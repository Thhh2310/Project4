package com.example.webbanmypham.service;

import com.example.webbanmypham.dto.OrderDetailDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDTO> getAll(Pageable pageable);

    int totalItem();

    OrderDetailDTO getByOrderDetailID(int orderDetailID);
    List<OrderDetailDTO> getByOrderID(int orderID, Pageable pageable);
    OrderDetailDTO getByUserID(int userID, Pageable pageable);
    List<OrderDetailDTO> getByProductID(int productID, Pageable pageable);
    void deleteOrderDetail(int orderDetailDTO);
    void createOrderDetail(OrderDetailDTO orderDetailDTO);
    void updateOrderDetail(OrderDetailDTO orderDetailDTO);
}
