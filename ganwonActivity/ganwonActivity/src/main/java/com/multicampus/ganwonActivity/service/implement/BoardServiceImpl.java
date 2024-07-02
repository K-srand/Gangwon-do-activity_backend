package com.multicampus.ganwonActivity.service.implement;

import com.multicampus.ganwonActivity.dto.request.board.PatchBoardRequestDto;
import com.multicampus.ganwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.ganwonActivity.dto.request.board.PostCommentRequestDto;
import com.multicampus.ganwonActivity.dto.response.ResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.DeleteBoardResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PatchBoardResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PostBoardResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PostCommentResponseDto;
import com.multicampus.ganwonActivity.entity.BoardEntity;
import com.multicampus.ganwonActivity.entity.CommentEntity;
import com.multicampus.ganwonActivity.repository.BoardRepository;
import com.multicampus.ganwonActivity.repository.CommentRepository;
import com.multicampus.ganwonActivity.repository.UserRepository;
import com.multicampus.ganwonActivity.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
//    private final BoardImageRepository boardImageRepository;
    private final CommentRepository commentRepository;


    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String id) {

        try {

            boolean isExistedId = userRepository.existsByUserId(id);
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
                    .build();

            boardRepository.save(boardEntity);

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostBoardResponseDto.success();
    }

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
    public List<BoardEntity> listBoard(){
//        try {
//
//        } catch (Exception e){
//            e.printStackTrace();
//
//        }
        // deletedTime 데이터 활용

        return  boardRepository.findAllButNotDeleted();
    }

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

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(Long boardNo, PostCommentRequestDto dto, String id) {

        try {
            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

            // 사용자 존재 확인
            boolean isExistedUser = userRepository.existsByUserId(id);
            if(!isExistedUser) return PostCommentResponseDto.noExistUser();

            // 게시글 존재 확인
            BoardEntity boardEntity = boardRepository.findByBoardNo(boardNo);
            if(boardEntity == null) return PostCommentResponseDto.noExistBoard();

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

}
