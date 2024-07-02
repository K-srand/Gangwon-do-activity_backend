package com.multicampus.ganwonActivity.repository;

import com.multicampus.ganwonActivity.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    void deleteByBoardNo(Long boardNo);

}
