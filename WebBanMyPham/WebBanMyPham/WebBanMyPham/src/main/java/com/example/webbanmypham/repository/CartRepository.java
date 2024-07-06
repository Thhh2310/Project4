package com.example.webbanmypham.repository;

import com.example.webbanmypham.entity.CartEntity;
import com.example.webbanmypham.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    Optional<CartEntity> findByCartID(int cartID);
    List<CartEntity> findByProductID(int productID,  Pageable pageable);
    List<CartEntity> findByUserID(UserEntity userID,  Pageable pageable);
    List<CartEntity> findByUserID(UserEntity userID);
    void deleteByCartID(int cartID);
    CartEntity saveAndFlush(CartEntity cartEntity);

    List<CartEntity> findAll(Pageable pageable);

    Object count();
}
