package com.multicampus.ganwonActivity.service.implement;

import com.multicampus.ganwonActivity.dto.request.board.PatchBoardRequestDto;
import com.multicampus.ganwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.ganwonActivity.dto.response.ResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PatchBoardResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PostBoardResponseDto;
import com.multicampus.ganwonActivity.entity.BoardEntity;
import com.multicampus.ganwonActivity.entity.UserEntity;
import com.multicampus.ganwonActivity.repository.BoardRepository;
import com.multicampus.ganwonActivity.repository.UserRepository;
import com.multicampus.ganwonActivity.service.BoardService;
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
                    .hasImage(false)
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
        return  boardRepository.findAll();
    }

    @Override
    public void deleteBoard(String id){
        boardRepository.deleteById(userRepository.findUserNoByUserId(id));
    }

}
