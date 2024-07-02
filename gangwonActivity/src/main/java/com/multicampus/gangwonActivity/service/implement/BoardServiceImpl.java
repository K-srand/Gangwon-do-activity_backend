package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.dto.request.board.PatchBoardRequestDto;
import com.multicampus.gangwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.PatchBoardResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.PostBoardResponseDto;
import com.multicampus.gangwonActivity.entity.BoardEntity;
import com.multicampus.gangwonActivity.mapper.BoardMapper;
import com.multicampus.gangwonActivity.repository.BoardRepository;
import com.multicampus.gangwonActivity.repository.UserRepository;
import com.multicampus.gangwonActivity.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;
    private final CommentRepository commentRepository;


    // 게시글 작성
    private final BoardMapper boardMapper;

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String id) {

        try {

            boolean isExistedId = userRepository.existsByUserId(id);
            if(!isExistedId) return PostBoardResponseDto.notExistUser();
            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
//            String time = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));

            BoardEntity boardEntity = BoardEntity.builder()
                    .boardTitle(dto.getTitle())
                    .content(dto.getContent())
                    .countLikes(0)
                    .deletedTime(null)
                    .isReported(false)
                    .myCourseNo(null)
                    .userNo(userRepository.findUserNoByUserId(id))
                    .writtenTime(localDateTime)
                    .boardCount(0)
                    .build();
            boardRepository.save(boardEntity);

            Long boardNo = boardEntity.getBoardNo();

            List<String> boardImageList = dto.getBoardImageList();
            List<BoardImageEntity> imageEntities = new ArrayList<>();

            for(String image : boardImageList){
//                BoardImageEntity imageEntity = new BoardImageEntity(boardNo, image);
                BoardImageEntity imageEntity = BoardImageEntity.builder()
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
            BoardEntity boardEntity = boardRepository.findByBoardNo(boardNo);

            boolean isExistedId = userRepository.existsByUserId(id);

            // 작성글 여부 확인
            if(boardEntity==null) return PatchBoardResponseDto.noExistBoard();

            // 사용자 여부 확인
            if(!isExistedId) return PatchBoardResponseDto.noExistUser();

            Long userNo = userRepository.findUserNoByUserId(id);

            // 나의 글 확인
            if(boardEntity.getUserNo() != userNo) return PatchBoardResponseDto.noPermission();

            boardEntity.setBoardTitle(dto.getTitle());
            boardEntity.setContent(dto.getContent());
            boardRepository.save(boardEntity);

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchBoardResponseDto.success();
    }

    @Override
    public List<GetBoardListResponseDto> listBoard(){
//        try {
//
//        } catch (Exception e){
//            e.printStackTrace();
//
//        }
//        return boardEntity~~~
        return boardMapper.findAllWithUser();
    }

    // 게시글 삭제
    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long boardNo, String id){
          LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        try{
            BoardEntity boardEntity = boardRepository.findByBoardNo(boardNo);

            boolean isExistedId = userRepository.existsByUserId(id);

            // 보드 존재 여부 확인
            if(boardEntity==null) return DeleteBoardResponseDto.noExistBoard();

            // 사용자 여부 확인
            if(!isExistedId) return DeleteBoardResponseDto.noExistUser();

            Long userNo = userRepository.findUserNoByUserId(id);

            // 나의 글 확인
            if(boardEntity.getUserNo() != userNo) return DeleteBoardResponseDto.noPermission();

            // 글 삭제 시 이미지와 댓글 같이 삭제
//            boardImageRepository.deleteByBoardNo(boardNo);
//            commentRepository.deleteByBoardNo(boardNo);
            boardEntity.setDeletedTime(localDateTime);
            boardRepository.save(boardEntity);


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
            if(!isExistedUser) return PostCommentResponseDto.noExistUser();


            BoardEntity boardEntity = boardRepository.findByBoardNo(boardNo);
            // 게시글 존재 확인
            if(boardEntity == null) {
                System.out.println("존재하지 않는 게시글입니다.");
                return PostCommentResponseDto.noExistBoard();
            }

            // 삭제 된 게시글인가
            if(boardEntity.getDeletedTime() != null) {
                System.out.println("삭제 된 게시글입니다.");
                return PostCommentResponseDto.noExistBoard();
            }


            CommentEntity commentEntity = CommentEntity.builder()
                    .boardNo(boardNo)
                    .userNo(userRepository.findUserNoByUserId(id))
                    .content(dto.getContent())
                    .writtenTime(localDateTime)
                    .build();

            commentRepository.save(commentEntity);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostCommentResponseDto.success();



}
