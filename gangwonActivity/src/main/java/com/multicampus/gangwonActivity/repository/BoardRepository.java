package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {


    BoardEntity findByBoardNo(Long boardNo);



}
