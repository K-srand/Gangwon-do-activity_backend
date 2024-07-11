package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    void deleteByBoardNo(Long boardNo);

}
