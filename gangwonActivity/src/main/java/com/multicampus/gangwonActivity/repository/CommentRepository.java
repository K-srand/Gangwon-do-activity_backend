package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.Board;
import com.multicampus.gangwonActivity.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteByBoardNo(Long boardNo);

    Board findByCommentNo(Long commentNo);

    Boolean existsByCommentNo(Long commentNo);

}
