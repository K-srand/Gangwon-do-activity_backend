package com.multicampus.gangwonActivity.dto.response.mypage;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class GetMyExpResponseDto extends ResponseDto {

    private Integer userExp;
    private Integer userRank;
    public GetMyExpResponseDto() { super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);}

    public static ResponseEntity<GetMyExpResponseDto> success(){
        GetMyExpResponseDto result = new GetMyExpResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
