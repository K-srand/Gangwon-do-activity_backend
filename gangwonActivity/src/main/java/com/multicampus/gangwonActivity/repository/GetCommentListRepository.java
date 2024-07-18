package com.multicampus.gangwonActivity.repository;

import java.time.LocalDateTime;

public interface GetCommentListRepository {

    Long commentNo();
    Long boardNo();
    Long userNo();
    LocalDateTime writtenTime();
    String content();

}
