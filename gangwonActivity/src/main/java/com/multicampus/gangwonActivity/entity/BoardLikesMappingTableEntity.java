package com.multicampus.gangwonActivity.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name="boardLikesMappingTable")
public class BoardLikesMappingTableEntity {

    @EmbeddedId
    private BoardLikesPK boardLikesPK;

}
