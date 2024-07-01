package com.multicampus.ganwonActivity.repository;

import com.multicampus.ganwonActivity.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {


    BoardEntity findByBoardNo(Long boardNo);



}
