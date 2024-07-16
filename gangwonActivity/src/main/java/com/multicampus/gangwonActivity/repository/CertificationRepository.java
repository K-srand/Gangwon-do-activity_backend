package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, String> {

    Certification findByEmail(String email);

}
