package com.example.webbanmypham.service;

import com.example.webbanmypham.dto.CartDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<CartDTO> getAll(Pageable pageable);
    int totalItem();
    CartDTO getByCartID(int cartID);
    List<CartDTO> getByProductID(int productID, Pageable pageable);
    List<CartDTO> getByStatus( int userID,Pageable pageable);
    List<CartDTO> getByUserID(int userID, Pageable pageable);
    void deleteCartByUserID(int userID);
    void deleteByCartID(int cartID);
    void createCart(CartDTO cartDTO);
    void updateCart(CartDTO cartDTO);
}
