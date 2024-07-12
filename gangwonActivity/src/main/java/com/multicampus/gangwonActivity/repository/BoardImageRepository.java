package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.BoardImageEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImageEntity, Long> {
    BoardImageEntity findByBoardNo(Long boardNo);

//    @Transactional
//    void deleteByBoardNo(Long boardNo);

}
