package com.example.webbanmypham.service;


import com.example.webbanmypham.dto.OrderDTO;
import com.example.webbanmypham.dto.OrderDetailDTO;
import com.example.webbanmypham.dto.OrderRequestDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAll(Pageable pageable);
    int totalItem();
    OrderDTO findByOrderID(int orderID);
    OrderRequestDTO getByOrderID(int orderID);    void deleteOrder(int orderID);
    void createOrder(OrderDTO orderDTO);
    void updateOrder(OrderDTO orderDTO);
    List<OrderDTO> getByOrderStatus(String orderStatus, Pageable pageable);
    List<OrderDTO> getByOrderPay(String orderPay, Pageable pageable);
    List<OrderDTO> getByUserID(int userID, Pageable pageable);
    List<OrderDTO> getByStatusUser(int userID, Pageable pageable);
    List<OrderDTO> getByStatusUser2(int userID, Pageable pageable);
    List<OrderDTO> getByStatusUser3(int userID, Pageable pageable);
    List<OrderDTO> getByOrderPayUser(int userID, Pageable pageable);
    List<OrderDTO> getByOrderPayUser2(int userID, Pageable pageable);
    List<OrderDTO> getByOrderCancelUser(int userID, Pageable pageable);
    List<OrderDTO> getByOrderCancelUser2(int userID, Pageable pageable);
    void placeOrder(OrderDTO order, List<OrderDetailDTO> orderDetailList);

    List<OrderDTO> getByOrderCancelUser(String orderCancel, Pageable pageable);

    void approveOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetailList);
}
