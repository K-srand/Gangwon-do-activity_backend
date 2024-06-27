package com.multicampus.ganwonActivity.service.implement;

import com.multicampus.ganwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.ganwonActivity.dto.response.ResponseDto;
import com.multicampus.ganwonActivity.dto.response.board.PostBoardResponseDto;
import com.multicampus.ganwonActivity.entity.BoardEntity;
import com.multicampus.ganwonActivity.repository.BoardRepository;
import com.multicampus.ganwonActivity.repository.UserRepository;
import com.multicampus.ganwonActivity.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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


        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }


        return PostBoardResponseDto.success();
    }
}
