package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


    Board findByBoardNo(Long boardNo);


    @Query(value = "select * from board where deletedTime is null", nativeQuery = true)
    List<Board> findAllButNotDeleted();


}
