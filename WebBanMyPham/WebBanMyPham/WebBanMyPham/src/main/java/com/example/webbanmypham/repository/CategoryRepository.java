package com.example.webbanmypham.repository;

import com.example.webbanmypham.entity.CategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findByCategoryID(int categoryID);
    List<CategoryEntity> findByCategoryName(String categoryName, Pageable pageable);
    void deleteByCategoryID(int categoryID);
    CategoryEntity saveAndFlush(CategoryEntity categoryEntity);
}
