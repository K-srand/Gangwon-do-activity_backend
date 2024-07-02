package com.multicampus.ganwonActivity.repository;

import com.multicampus.ganwonActivity.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {


    BoardEntity findByBoardNo(Long boardNo);


    @Query(value = "select * from board where deletedTime is null", nativeQuery = true)
    List<BoardEntity> findAllButNotDeleted();



}
