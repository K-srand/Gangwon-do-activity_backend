package com.multicampus.gangwonActivity.dto.response.board;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class BoardLikesResponseDto extends ResponseDto {

    private BoardLikesResponseDto() { super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);}

    public static ResponseEntity<BoardLikesResponseDto> success(){
        BoardLikesResponseDto result = new BoardLikesResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXITSTED_USER, ResponseMessage.NOT_EXITSTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

//    public static ResponseEntity<ResponseDto> alreadyLiked() {
//        ResponseDto result = new ResponseDto(ResponseCode.ALREADY_LIKED, ResponseMessage.ALREADY_LIKED);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//    }

    private Long boardNo;
    private Long userNo;

}
