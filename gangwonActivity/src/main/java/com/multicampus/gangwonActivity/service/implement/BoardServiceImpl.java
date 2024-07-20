package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.dto.request.board.PatchBoardRequestDto;
import com.multicampus.gangwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.gangwonActivity.dto.request.board.PostCommentRequestDto;
import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyCourseDto;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardCommentListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.*;
import com.multicampus.gangwonActivity.entity.*;
import com.multicampus.gangwonActivity.mapper.BoardMapper;
import com.multicampus.gangwonActivity.repository.*;
import com.multicampus.gangwonActivity.service.BoardService;
import com.multicampus.gangwonActivity.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;
    private final CommentRepository commentRepository;
    private final BoardLikesMappingTableRepository boardLikesMappingTableRepository;


    private final BoardMapper boardMapper;

    @Autowired
    private S3Service s3Service;

    //게시글 작성
    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String id) {

        try {

            //일반 유저
            boolean isExistedId = userRepository.existsByUserId(id);
            if(!isExistedId) return PostBoardResponseDto.notExistUser();
            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
//            String time = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));

            //작성글 데이터 저장
            Board board = Board.builder()
                    .boardTitle(dto.getTitle())
                    .content(dto.getContent())
                    .countLikes(0)
                    .deletedTime(null)
                    .isReported(false)
                    .myCourseNo(dto.getMyCourseNo())
                    .userNo(userRepository.findUserNoByUserId(id))
                    .writtenTime(localDateTime)
                    .boardCount(0)
                    .build();
            boardRepository.save(board);

            Long boardNo = board.getBoardNo();

            //작성글 이미지 저장
            List<String> boardImageList = dto.getBoardImageList();
            List<BoardImage> imageEntities = new ArrayList<>();

            for (String image : boardImageList) {
                BoardImage imageEntity = BoardImage.builder()
                        .boardNo(boardNo)
                        .imageAddress(image)
                        .build();
                imageEntities.add(imageEntity);
            }
            boardImageRepository.saveAll(imageEntities);

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostBoardResponseDto.success();
    }

    // 게시글 수정
    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(Long boardNo, PatchBoardRequestDto dto, String id) {

        try {
            Board board = boardRepository.findByBoardNo(boardNo);

            // 사용자 여부 확인
            boolean isExistedId = userRepository.existsByUserId(id);
            if(!isExistedId) return PatchBoardResponseDto.noExistUser();

            // 작성글 여부 확인
            if(board ==null) return PatchBoardResponseDto.noExistBoard();

            Long userNo = userRepository.findUserNoByUserId(id);

            // 나의 글 확인
            if(board.getUserNo() != userNo) return PatchBoardResponseDto.noPermission();

            //수정된 데이터 저장
            board.setBoardTitle(dto.getTitle());
            board.setContent(dto.getContent());
            boardRepository.save(board);

            List<String> boardImageList = dto.getBoardImageList();
            List<BoardImage> imageEntities = new ArrayList<>();
            if(boardImageList != null) {
                for (String image : boardImageList) {
                    BoardImage imageEntity = BoardImage.builder()
                            .boardNo(boardNo)
                            .imageAddress(image)
                            .build();
                    imageEntities.add(imageEntity);
                }
            }

            boardImageRepository.saveAll(imageEntities);

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchBoardResponseDto.success();
    }

    //작성글 목록
    @Override
    public List<GetBoardListResponseDto> listBoard(SearchPageDto searchPageDto){
        return boardMapper.findAllWithUser(searchPageDto);
    }

    //작성글 삭제
    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long boardNo, String id){

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        try{
            Board board = boardRepository.findByBoardNo(boardNo);

            // 사용자 여부 확인
            boolean isExistedId = userRepository.existsByUserId(id);
            if(!isExistedId) return DeleteBoardResponseDto.noExistUser();

            // 보드 존재 여부 확인
            if(board ==null) return DeleteBoardResponseDto.noExistBoard();
            Long userNo = userRepository.findUserNoByUserId(id);

            // 나의 글 확인
            if(board.getUserNo() != userNo) return DeleteBoardResponseDto.noPermission();

            // 글 삭제 시 이미지와 댓글 같이 삭제
//            boardImageRepository.deleteByBoardNo(boardNo);
//            commentRepository.deleteByBoardNo(boardNo);
            board.setDeletedTime(localDateTime);
            boardRepository.save(board);


        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteBoardResponseDto.success();

    }

    // 댓글 입력
    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(Long boardNo, PostCommentRequestDto dto, String id) {

        try {
            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

            // 사용자 존재 확인
            boolean isExistedUser = userRepository.existsByUserId(id);
            if (!isExistedUser) return PostCommentResponseDto.noExistUser();

            // 삭제 된 게시글인가
            Board board = boardRepository.findByBoardNo(boardNo);
            if (board == null || board.getDeletedTime() != null) {
                return PostCommentResponseDto.noExistBoard();
            }

            //입력받은 댓글 데이터 저장
            Long userNo = userRepository.findUserNoByUserId(id);
            Comment comment = Comment.builder()
                    .boardNo(boardNo)
                    .userNo(userNo)
                    .content(dto.getContent())
                    .writtenTime(localDateTime)
                    .build();

            commentRepository.save(comment);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostCommentResponseDto.success();

    }

    // 좋아요 기능
    @Override
    public ResponseEntity<? super BoardLikesResponseDto> likesBoard(Long boardNo, String id) {

        try {
            //사용자 존재 여부 확인
            boolean isExistedUser = userRepository.existsByUserId(id);
            if (!isExistedUser) return BoardLikesResponseDto.noExistUser();

            //작성글 존재 여부 확인
            Board board = boardRepository.findByBoardNo(boardNo);
            if (board == null) return BoardLikesResponseDto.noExistBoard();

            Long userNo = userRepository.findUserNoByUserId(id);
            String check = boardMapper.likeChecked(boardNo, userNo);

            if ("likes".equals(check)) {
                // 이미 좋아요 상태인 경우 좋아요 취소
                unlike(boardNo, id);
                board.setCountLikes(board.getCountLikes() - 1);
                boardRepository.save(board);
            } else {
                // 그게 아니면 좋아요
                if ("dislikes".equals(check)) {
                    // 싫어요 상태인 경우 싫어요를 취소하고 좋아요
                    undislike(boardNo, id);
                    board.setCountLikes(board.getCountLikes() + 1);
                }
                like(boardNo, id);
                board.setCountLikes(board.getCountLikes() + 1);
                boardRepository.save(board);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return BoardLikesResponseDto.success();
    }

    // 싫어요 기능
    @Override
    public ResponseEntity<? super BoardLikesResponseDto> dislikesBoard(Long boardNo, String id) {
        try {
            // 사용자 존재 여부 확인
            boolean isExistedUser = userRepository.existsByUserId(id);
            if (!isExistedUser) return BoardLikesResponseDto.noExistUser();

            // 작성글 존재 여부 확인
            Board board = boardRepository.findByBoardNo(boardNo);
            if (board == null) return BoardLikesResponseDto.noExistBoard();

            // 좋아요 한 번 제한
            Long userNo = userRepository.findUserNoByUserId(id);
            String check = boardMapper.likeChecked(boardNo, userNo);

            if ("dislikes".equals(check)) {
                // 이미 싫어요 상태인 경우 싫어요 취소
                undislike(boardNo, id);
                board.setCountLikes(board.getCountLikes() + 1);
                boardRepository.save(board);
            } else {
                // 그게 아니면 싫어요
                if ("likes".equals(check)) {
                    // 좋아요 상태인 경우 좋아요를 취소하고 싫어요
                    unlike(boardNo, id);
                    board.setCountLikes(board.getCountLikes() - 1);
                }
                dislike(boardNo, id);
                board.setCountLikes(board.getCountLikes() - 1);
                boardRepository.save(board);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();

        }

        return BoardLikesResponseDto.success();
    }

    //작성글 상세
    @Override
    public GetBoardDetailResponseDto getBoardDetail(Long boardNo, String id) {

        //작성글 정보 조회
        GetBoardDetailResponseDto boardDetail = boardMapper.findBoardDetailWithUser(boardNo);
        boardDetail.setFirstImage2(boardMapper.selectMyCourse(boardDetail.getMyCourseNo()));

        //작성글 댓글 목록 조회
        List<GetBoardCommentListResponseDto> comments = boardMapper.findCommentsByBoardNo(boardNo, new SearchPageDto());

        boardDetail.setComments(comments);
        incrementViews(boardNo);
        return boardDetail;
    }

    //전체 작성글 수 조회
    @Override
    public int countBoard() {
        return boardMapper.countAllWithBoard();
    }

    //해당 작성글 댓글 수 조회
    @Override
    public int countComment(Long boardNo) {
        return boardMapper.countAllWithComment(boardNo);
    }

    //이미지 URL 불러오기
    @Override
    public List<String> getImageAddress(Long boardNo) {

        return boardMapper.findAllImage(boardNo);

    }

    //해당 작성글 댓글 목록
    @Override
    public List<GetBoardCommentListResponseDto> getBoardCommentList(Long boardNo, SearchPageDto searchPageDto) {
        return boardMapper.findCommentsByBoardNo(boardNo, searchPageDto);
    }

    //조회수 증가
    public void incrementViews(Long boardNo) {
        boardMapper.incrementViewsByBoardNo(boardNo);
    }

    //추천글 3개 조회
    @Override
    public List<GetBoardListResponseDto> getBestPosts() {
        return boardMapper.getBestPosts();
    }

    //예원
    @Override
    public void like(Long boardNo, String id) {
        Long userNo = userRepository.findUserNoByUserId(id);
        boardMapper.like(boardNo, userNo);
    }

    @Override
    public void dislike(Long boardNo, String id) {
        Long userNo = userRepository.findUserNoByUserId(id);
        boardMapper.dislike(boardNo, userNo);
    }

    @Override
    public String likeChecked(long boardNo, long userNo) {
        return boardMapper.likeChecked(boardNo, userNo);
    }

    @Override
    public void unlike(long boardNo, String id) {
        Long userNo = userRepository.findUserNoByUserId(id);
        boardMapper.unlike(boardNo, userNo);
    }

    @Override
    public void undislike(long boardNo, String id) {
        Long userNo = userRepository.findUserNoByUserId(id);
        boardMapper.undislike(boardNo, userNo);
    }

    //낌&빡
    //나만의 코스 불러오기
    @Override
    public List<Map<String, Object>> getMyCourse(String userId) {
        return boardMapper.findMyCourse(boardMapper.selectUserNo(userId));
    }

    //전체 나만의 코스 수 조회
    public int countMyCourse(String userId) {
        return boardMapper.countMyCourse(boardMapper.selectUserNo(userId));
    }

    public void deleteMyCourse(Long boardNo) {
        boardMapper.deleteMyCourse(boardNo);
    }

    public void updateMyCourse(Long boardNo, Long myCourseNo) {
        boardMapper.updateMyCourse(boardNo, myCourseNo);
    }

    public void incrementExp3(String id){
        Long userNo = userRepository.findUserNoByUserId(id);
        boardMapper.incrementExpBoardWrite(userNo);
    }

    public void incrementExp1(String id){
        Long userNo = userRepository.findUserNoByUserId(id);
        boardMapper.incrementExpCommentWrite(userNo);
    }
}