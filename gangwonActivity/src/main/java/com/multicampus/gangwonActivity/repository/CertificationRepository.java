package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.BoardEntity;
import com.multicampus.gangwonActivity.entity.CertificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, String> {

    CertificationEntity findByEmail(String email);

}
