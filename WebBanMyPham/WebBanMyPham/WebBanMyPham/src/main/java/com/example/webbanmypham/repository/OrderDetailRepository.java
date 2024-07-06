package com.example.webbanmypham.repository;

import com.example.webbanmypham.entity.OrderDetailEntity;
import com.example.webbanmypham.entity.OrderEntity;
import com.example.webbanmypham.entity.ProductEntity;
import com.example.webbanmypham.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {
    Optional<OrderDetailEntity> findByOrderDetailID(int orderDetailID);

    List<OrderDetailEntity> findByProductID(ProductEntity product, Pageable pageable);
    List<OrderDetailEntity> findByOrderID(OrderEntity order, Pageable pageable);

    static List<OrderDetailEntity> findByOrderID(OrderEntity order) {
        return null;
    }

    void deleteByOrderDetailID(int orderDetailID);
    OrderDetailEntity saveAndFlush(OrderDetailEntity orderDetailEntity);
    List<OrderDetailEntity> findAll(Pageable pageable);
    Object count();
}
