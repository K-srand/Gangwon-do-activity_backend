package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.BoardLikesUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikesMappingTableRepository extends JpaRepository<BoardLikesUser, Long> {
}