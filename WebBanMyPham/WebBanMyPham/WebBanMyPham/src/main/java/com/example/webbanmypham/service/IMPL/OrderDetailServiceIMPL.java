package com.example.webbanmypham.service.IMPL;

import com.example.webbanmypham.dto.OrderDetailDTO;
import com.example.webbanmypham.entity.OrderDetailEntity;
import com.example.webbanmypham.entity.OrderEntity;
import com.example.webbanmypham.entity.ProductEntity;
import com.example.webbanmypham.repository.OrderDetailRepository;
import com.example.webbanmypham.repository.OrderRepository;
import com.example.webbanmypham.repository.ProductRepository;
import com.example.webbanmypham.service.OrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import mapper.Opject.OrderDetailMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailServiceIMPL implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;
    private OrderDetailMapper orderDetailMapper;
    public OrderDetailServiceIMPL(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, ProductRepository productsRepository, ModelMapper modelMapper, OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productsRepository;
        this.modelMapper = modelMapper;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public List<OrderDetailDTO> getAll(Pageable pageable) {
        List<OrderDetailDTO> results = new ArrayList<>();
        List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findAll(pageable);
        for (OrderDetailEntity item: orderDetailEntities
        ) {
            OrderDetailDTO DTO = orderDetailMapper.maptoDTO(item);
            results.add(DTO);
        }
        return results;
    }
    @Override
    public int totalItem() {
        return (int)orderDetailRepository.count();
    }


    @Override
    public OrderDetailDTO getByOrderDetailID(int orderDetailID) {
        try {
            OrderDetailEntity orderDetailEntity = orderDetailRepository.findByOrderDetailID(orderDetailID)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + orderDetailID));
            return orderDetailMapper.maptoDTO(orderDetailEntity);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public List<OrderDetailDTO> getByOrderID(int orderID, Pageable pageable) {
        List<OrderDetailDTO> results = new ArrayList<>();
        OrderEntity order = orderRepository.findByOrderID(orderID).orElse(null);
        List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findByOrderID(order,pageable);
        for (OrderDetailEntity item: orderDetailEntities
        ) {
            OrderDetailDTO DTO = orderDetailMapper.maptoDTO(item);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public OrderDetailDTO getByUserID(int userID, Pageable pageable) {
        return null;
    }


    @Override
    public List<OrderDetailDTO> getByProductID(int productID, Pageable pageable) {
        List<OrderDetailDTO> results = new ArrayList<>();
        ProductEntity products = productRepository.findByProductID(productID).orElse(null);
        List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findByProductID(products,pageable);
        for (OrderDetailEntity item: orderDetailEntities
        ) {
            OrderDetailDTO DTO = orderDetailMapper.maptoDTO(item);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public void deleteOrderDetail(int orderDetailDTO) {
        orderDetailRepository.deleteByOrderDetailID(orderDetailDTO);

    }

    @Override
    public void createOrderDetail(OrderDetailDTO orderDetailDTO) {
        if ( orderDetailDTO != null) {
            OrderDetailEntity orderDetailEntity = modelMapper.map(orderDetailDTO, OrderDetailEntity.class);
            ProductEntity product = productRepository.findByProductID(orderDetailDTO.getProductID()).orElse(null);
            if (orderDetailEntity != null) {
                orderDetailEntity.setProductID(product);
                orderDetailRepository.saveAndFlush(orderDetailEntity);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }

    @Override
    public void updateOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetailEntity existingOrderDetail  = orderDetailRepository.findByOrderDetailID(orderDetailDTO.getOrderDetailID())
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu User"));
        modelMapper.map(orderDetailDTO, existingOrderDetail);
        orderDetailRepository.saveAndFlush(existingOrderDetail);
    }
}
