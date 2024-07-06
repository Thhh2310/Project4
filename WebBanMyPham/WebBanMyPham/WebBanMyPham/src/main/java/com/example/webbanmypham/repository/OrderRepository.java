package com.example.webbanmypham.repository;

import com.example.webbanmypham.entity.OrderEntity;
import com.example.webbanmypham.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<OrderEntity> findByOrderID(int orderID);
    List<OrderEntity> findByOrderStatus(String orderStatus, Pageable pageable);
    List<OrderEntity> findByOrderPay(String orderPay, Pageable pageable);
    List<OrderEntity> findByOrderCancel(String orderCancel, Pageable pageable);
    List<OrderEntity> findByUserID(UserEntity userid, Pageable pageable);
    void deleteByOrderID(int orderID);
    OrderEntity saveAndFlush(OrderEntity orderEntity);

    List<OrderEntity> findAll(Pageable pageable);

    Object count();
}
