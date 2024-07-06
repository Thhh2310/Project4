package com.example.webbanmypham.service.IMPL;

import com.example.webbanmypham.dto.OrderDTO;

import com.example.webbanmypham.dto.OrderDetailDTO;
import com.example.webbanmypham.dto.OrderRequestDTO;
import com.example.webbanmypham.entity.*;

import com.example.webbanmypham.repository.*;
import com.example.webbanmypham.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import mapper.Opject.OrderMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceIMPL implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private UserRepository userRepository;
    private OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private OrderEntity orderEntity;
    private ProductEntity productEntity;
    public OrderServiceIMPL(OrderRepository orderRepository, OrderMapper orderMapper, ModelMapper modelMapper,  UserRepository userRepository, OrderDetailRepository orderDetailRepository, ProductRepository productsRepository, OrderEntity orderEntity, ProductEntity productEntity) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productsRepository;
        this.orderEntity = orderEntity;
        this.productEntity = productEntity;
    }

    @Override
    public List<OrderDTO> getAll(Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        List<OrderEntity> orderEntities = orderRepository.findAll(pageable);
        for (OrderEntity item: orderEntities
        ) {
            OrderDTO DTO = orderMapper.maptoDTO(item);
            results.add(DTO);
        }
        return results;
    }

//    @Override
//    public OrderDTO getByOrderID(int orderID) {
//        try {
//            OrderEntity order = orderRepository.findByOrderID(orderID)
//                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + orderID));
//            return modelMapper.map(order, OrderDTO.class);
//        } catch (EntityNotFoundException ex) {
//            throw ex;
//        } catch (Exception e) {
//            throw new RuntimeException("An error occurred while fetching data by ID", e);
//        }
//    }
    @Override
    public int totalItem() {
        return (int)orderRepository.count();
    }


    @Override
    public OrderDTO findByOrderID(int orderID) {
        try {
            OrderEntity order = orderRepository.findByOrderID(orderID)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + orderID));
            return orderMapper.maptoDTO(order);

        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }
    @Override
    public OrderRequestDTO getByOrderID(int orderID) {
        try {
            OrderEntity orderEntity = orderRepository.findByOrderID(orderID)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + orderID));
            List<OrderDetailEntity> orderDetailEntities = OrderDetailRepository.findByOrderID(orderEntity);
            OrderDTO orderOTD = modelMapper.map(orderEntity, OrderDTO.class);
            List<OrderDetailDTO> orderDTOList = new ArrayList<>();
            OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
            for (OrderDetailEntity item : orderDetailEntities
            ) {
                OrderDetailDTO dto = modelMapper.map(item, OrderDetailDTO.class);
                orderDTOList.add(dto);
            }
            orderRequestDTO.setOrder(orderOTD);
            orderRequestDTO.setOrderDetailList(orderDTOList);
            return orderRequestDTO;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }

    }
    @Override
    public List<OrderDTO> getByOrderStatus(String orderStatus, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        List<OrderEntity> orderDetailEntities = orderRepository.findByOrderStatus(orderStatus,pageable);
        for (OrderEntity item: orderDetailEntities
        ) {
            OrderDTO DTO = modelMapper.map(item, OrderDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<OrderDTO> getByOrderPay(String orderPay, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        List<OrderEntity> orderDetailEntities = orderRepository.findByOrderPay(orderPay,pageable);
        for (OrderEntity item: orderDetailEntities
        ) {
            OrderDTO DTO = modelMapper.map(item, OrderDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<OrderDTO> getByUserID(int userID, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        UserEntity user = userRepository.findByUserID(userID).orElse(null);
        List<OrderEntity> orderEntities = orderRepository.findByUserID(user, pageable);
        for (OrderEntity item: orderEntities
        ) {
            OrderDTO DTO = orderMapper.maptoDTO(item);
            results.add(DTO);
        }
        return results;
    }
    @Override
    public List<OrderDTO> getByStatusUser(int userID, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        UserEntity user = userRepository.findByUserID(userID).orElse(null);
        List<OrderEntity> orderEntities = orderRepository.findByUserID(user, pageable);
        for (OrderEntity item: orderEntities
        ) {
            if (item.getOrderStatus().equals("Đang chuẩn bị hàng") && item.getOrderCancel().equals("Xác Nhận") ){
                OrderDTO DTO = orderMapper.maptoDTO(item);
                results.add(DTO);
            }
        }
        return results;
    }

    @Override
    public List<OrderDTO> getByStatusUser2(int userID, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        UserEntity user = userRepository.findByUserID(userID).orElse(null);
        List<OrderEntity> orderEntities = orderRepository.findByUserID(user, pageable);
        for (OrderEntity item : orderEntities
        ) {
            if (item.getOrderStatus().equals("Đang giao hàng") && item.getOrderCancel().equals("Xác Nhận")) {
                OrderDTO DTO = modelMapper.map(item, OrderDTO.class);
                results.add(DTO);
            }
        }
        return results;
    }

    @Override
    public List<OrderDTO> getByStatusUser3(int userID, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        UserEntity user = userRepository.findByUserID(userID).orElse(null);
        List<OrderEntity> orderEntities = orderRepository.findByUserID(user, pageable);
        for (OrderEntity item : orderEntities
        ) {
            if (item.getOrderStatus().equals("Đã giao ") && item.getOrderCancel().equals("Xác Nhận") && item.getOrderPay().equals("Đã thanh toán")) {
                OrderDTO DTO = modelMapper.map(item, OrderDTO.class);
                results.add(DTO);
            }
        }
        return results;
    }

    @Override
    public List<OrderDTO> getByOrderPayUser(int userID, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        UserEntity user = userRepository.findByUserID(userID).orElse(null);
        List<OrderEntity> orderEntities = orderRepository.findByUserID(user, pageable);
        for (OrderEntity item : orderEntities
        ) {
            if (item.getOrderPay().equals("Đã thanh toán") && item.getOrderCancel().equals("Xác Nhận")) {
                OrderDTO DTO = modelMapper.map(item, OrderDTO.class);
                results.add(DTO);
            }
        }
        return results;
    }

    @Override
    public List<OrderDTO> getByOrderPayUser2(int userID, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        UserEntity user = userRepository.findByUserID(userID).orElse(null);
        List<OrderEntity> orderEntities = orderRepository.findByUserID(user,pageable);
        for (OrderEntity item : orderEntities
        ) {
            if (item.getOrderPay().equals("Chưa thanh toán") && item.getOrderCancel().equals("Xác Nhận")) {
                OrderDTO DTO = modelMapper.map(item, OrderDTO.class);
                results.add(DTO);
            }
        }
        return results;
    }

    @Override
    public List<OrderDTO> getByOrderCancelUser(int userID, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        UserEntity user = userRepository.findByUserID(userID).orElse(null);
        List<OrderEntity> orderEntities = orderRepository.findByUserID(user, pageable);
        for (OrderEntity item : orderEntities
        ) {
            if (item.getOrderCancel().equals("Xác Nhận")) {
                OrderDTO DTO = modelMapper.map(item, OrderDTO.class);
                results.add(DTO);
            }
        }
        return results;
    }

    @Override
    public List<OrderDTO> getByOrderCancelUser2(int userID, Pageable pageable) {
        List<OrderDTO> results = new ArrayList<>();
        UserEntity user = userRepository.findByUserID(userID).orElse(null);
        List<OrderEntity> orderEntities = orderRepository.findByUserID(user, pageable);
        for (OrderEntity item : orderEntities
        ) {
            if (item.getOrderCancel().equals("Đã hủy")) {
                OrderDTO DTO = modelMapper.map(item, OrderDTO.class);
                results.add(DTO);
            }
        }
        return results;
    }

    @Override
    public void placeOrder(OrderDTO order, List<OrderDetailDTO> orderDetailList) {
        if (order == null || orderDetailList == null || orderDetailList.isEmpty()) {
            throw new IllegalArgumentException("Order and order details cannot be null or empty");
        }

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        UserEntity user = userRepository.findByUserID(order.getUserID()).orElse(null);

        if (orderEntity == null || user == null) {
            throw new RuntimeException("Order or User not found");
        }

        orderEntity.setUserID(user);
        orderEntity.setOrderDate(sqlDate);
        orderEntity.setOrderCancel("Xác Nhận");
        orderEntity.setOrderPay("Chưa thanh toán");
        orderEntity.setOrderStatus("Đang chuẩn bị hàng");

        OrderEntity savedOrder = orderRepository.saveAndFlush(orderEntity);

        for (OrderDetailDTO item : orderDetailList) {
            OrderDetailEntity orderDetail = modelMapper.map(item, OrderDetailEntity.class);
            ProductEntity product = productRepository.findByProductID(item.getProductID()).orElse(null);

            if (product == null || product.getQuantity() <= 0 || product.getQuantity() < orderDetail.getQuantity()) {
                throw new RuntimeException("Sản phẩm đã hết hàng hoặc không đủ số lượng");
            }

            orderDetail.setProductID(product);
            orderDetail.setOrderID(savedOrder);

            BigDecimal quantity = BigDecimal.valueOf(orderDetail.getQuantity());
            BigDecimal totalAmount = quantity.multiply(product.getPrice());
            orderDetail.setTotalAmount(totalAmount);

            product.setProductID(product.getQuantity() - orderDetail.getQuantity());
            orderDetailRepository.saveAndFlush(orderDetail);
            productRepository.save(product);
        }
    }

    @Override
    public List<OrderDTO> getByOrderCancelUser(String orderCancel, Pageable pageable) {
        return List.of();
    }

    @Override
    public void approveOrder(OrderDTO order, List<OrderDetailDTO> orderDetailList) {
        Date date = new Date();
        OrderEntity orderEntity = orderRepository.findByOrderID(order.getOrderID()).orElseThrow(()-> new RuntimeException("khong co du lieu"));
        if (orderEntity != null && orderDetailList.size() != 0 ){
            OrderEntity saveOrder = orderRepository.saveAndFlush(orderEntity);
            for ( OrderDetailDTO item : orderDetailList
            ) {
                List<OrderDetailEntity>  orderDetail = OrderDetailRepository.findByOrderID(orderEntity);
                for (OrderDetailEntity orderItem: orderDetail
                ) {
                    orderDetailRepository.saveAndFlush(orderItem);
                }
            }
        }
    }
    @Override
    public void deleteOrder(int orderID) {
        orderRepository.deleteByOrderID(orderID);

    }

    @Override
    public void createOrder(OrderDTO OrderDTO) {
        if (OrderDTO != null) {
            OrderEntity order = modelMapper.map(OrderDTO, OrderEntity.class);
            UserEntity user = userRepository.findByUserID(OrderDTO.getUserID()).orElse(null);
            if (order != null) {
                order.setUserID(user);
                orderRepository.saveAndFlush(order);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }

    @Override
    public void updateOrder(OrderDTO OrderDTO) {
        OrderEntity existinOrder = orderRepository.findByOrderID(OrderDTO.getOrderID())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu User"));
        modelMapper.map(OrderDTO, existinOrder);
        orderRepository.saveAndFlush(existinOrder);
    }
}
