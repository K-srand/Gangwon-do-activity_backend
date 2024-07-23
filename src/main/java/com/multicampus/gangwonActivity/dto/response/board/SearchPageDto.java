package com.multicampus.gangwonActivity.dto.response.board;

import lombok.Data;

@Data
public class SearchPageDto {
    private int page = 0; // 현재 페이지 번호
    private int size = 10; //dafault 값으로 10개 지정

    public int getOffset() { //Offset 학습하세요.
        return page * size;
    }
}