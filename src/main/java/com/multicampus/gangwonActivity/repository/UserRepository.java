package com.multicampus.gangwonActivity.repository;


import com.multicampus.gangwonActivity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserEmail(String userEmail);
    User findByUserId(String userId);
    boolean existsByUserEmail(String email);
    boolean existsByUserNick(String nickname);
    boolean existsByUserId(String id);



    @Query(value = "select userNo from user where userId= ?1", nativeQuery = true)
    Long findUserNoByUserId(String id);

}
