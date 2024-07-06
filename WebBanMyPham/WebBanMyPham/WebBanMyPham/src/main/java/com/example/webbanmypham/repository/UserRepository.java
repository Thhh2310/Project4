package com.example.webbanmypham.repository;

import com.example.webbanmypham.dto.UserDTO;
import com.example.webbanmypham.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
//    @Query("select u from UserEntity u where u.userName like %:username%")
//        List<UserEntity> findByUserName(@Param("username") String username);
//        void deleteByUserID(int userID);
//    UserEntity findByUserID(UserDTO userID);
    Optional<UserEntity> findByUserID(UserDTO userID);
    List<UserEntity> findByUserName(String userName, Pageable pageable);
    List<UserEntity> findByUserName(String userName);
    List<UserEntity> findByEmail(String email);
    List<UserEntity> findByStatus(Boolean status);
    void deleteByUserID(int userID);
    UserEntity saveAndFlush(UserEntity userEntity);

    UserEntity findByIsAdmin(int userID);

    List<UserEntity> finByIsAdmin(UserEntity userEntity, Pageable pageable);

    UserEntity findByIsAdmin(String isAdmin);

    Optional<UserEntity> findByUserID(int userID);
}
