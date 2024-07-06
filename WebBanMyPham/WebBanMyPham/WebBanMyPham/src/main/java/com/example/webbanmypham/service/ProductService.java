package com.example.webbanmypham.service;


import com.example.webbanmypham.dto.ProductDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> getAll(Pageable pageable);
    int totalItem();
    ProductDTO getByProductID(int productID);
    List<ProductDTO> getByProductName(String productName, Pageable pageable);
    List<ProductDTO> getByProductPrice(int productPrice, Pageable pageable);
    List<ProductDTO> getByCategoryID(int categoryID, Pageable pageable);
    void deleteProduct(int productID);
    ResponseEntity<?> createProduct(ProductDTO productDTO);
    void updateProduct(ProductDTO productDTO);
}
