package com.multicampus.ganwonActivity.repository;


import com.multicampus.ganwonActivity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserEmail(String userEmail);
    UserEntity findByUserId(String userId);
    boolean existsByUserEmail(String email);

    boolean existsByUserNick(String nickname);
    boolean existsByUserId(String id);

}
