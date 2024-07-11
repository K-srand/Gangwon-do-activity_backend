package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.BoardLikesMappingTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikesMappingTableRepository extends JpaRepository<BoardLikesMappingTableEntity, Long> {
}