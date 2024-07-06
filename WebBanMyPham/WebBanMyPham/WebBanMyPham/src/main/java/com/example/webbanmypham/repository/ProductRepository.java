package com.example.webbanmypham.repository;

import com.example.webbanmypham.dto.ProductDTO;
import com.example.webbanmypham.entity.CategoryEntity;
import com.example.webbanmypham.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
//    Optional<ProductEntity> findByProductID(int productID);
//    Optional<ProductEntity> findByProductID(ProductDTO productID);
//
//    List<ProductEntity> findByProductName(String productName);
//
//    void deleteByProductID(int productID);
//
//    ProductEntity saveAndFlush(ProductEntity productEntity);
    Optional<ProductEntity> findByProductID(int productID);
    List<ProductEntity> findByProductName(String productName);
    List<ProductEntity> findByProductName(String productName, Pageable pageable);
    List<ProductEntity> findByProductPrice(int productPrice, Pageable pageable);
    List<ProductEntity> findByCategoryID(CategoryEntity categoryEntity, Pageable pageable);

    void deleteByProductID(int productID);
    ProductEntity saveAndFlush(ProductEntity productsEntity);

    Optional<ProductEntity> findByProductID(ProductDTO productID);
}
