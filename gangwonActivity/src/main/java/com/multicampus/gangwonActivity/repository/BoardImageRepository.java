package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {
    BoardImage findByBoardNo(Long boardNo);

//    @Transactional
//    void deleteByBoardNo(Long boardNo);

}
