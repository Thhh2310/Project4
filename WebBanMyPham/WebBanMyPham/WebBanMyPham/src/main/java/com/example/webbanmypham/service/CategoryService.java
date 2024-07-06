package com.example.webbanmypham.service;

import com.example.webbanmypham.dto.CategoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAll(Pageable pageable);
    int totalItem();
    CategoryDTO getByCategoryID(int categoryID);
    List<CategoryDTO> getByCategoryName(String categoryName, Pageable pageable);
    void deleteCategory(int categoryID);
    void createCategory(CategoryDTO categoryDTO);
    void updateCategory(CategoryDTO categoryDTO);
}
