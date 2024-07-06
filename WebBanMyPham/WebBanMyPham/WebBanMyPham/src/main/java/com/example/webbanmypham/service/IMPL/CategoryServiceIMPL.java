package com.example.webbanmypham.service.IMPL;

import com.example.webbanmypham.dto.CategoryDTO;
import com.example.webbanmypham.entity.CategoryEntity;
import com.example.webbanmypham.repository.CategoryRepository;
import com.example.webbanmypham.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import mapper.Opject.CategoryMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceIMPL implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
    private CategoryMapper categoryMapper;
    public CategoryServiceIMPL(CategoryRepository categoryRepository, ModelMapper modelMapper, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAll(Pageable pageable) {
        List<CategoryDTO> results = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable).getContent();
        for (CategoryEntity item: categoryEntities
        ) {
            CategoryDTO DTO = categoryMapper.maptoDTO(item);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public CategoryDTO getByCategoryID(int categoryID) {
        try {
            CategoryEntity category = categoryRepository.findByCategoryID(categoryID)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + categoryID));
            return modelMapper.map(category, CategoryDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }
    @Override
    public int totalItem() {
        return (int) categoryRepository.count();
    }

    @Override
    public List<CategoryDTO> getByCategoryName(String categoryName,Pageable pageable) {
        List<CategoryDTO> results = new ArrayList<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findByCategoryName(categoryName,pageable);
        for (CategoryEntity item: categoryEntities
        ) {
            CategoryDTO DTO = categoryMapper.maptoDTO(item);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public void deleteCategory(int categoryID) {
        categoryRepository.deleteByCategoryID(categoryID);
    }

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO != null) {
            CategoryEntity category = modelMapper.map(categoryDTO, CategoryEntity.class);
            if (category != null) {
                categoryRepository.save(category);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        CategoryEntity existingCategory  = categoryRepository.findByCategoryID(categoryDTO.getCategoryID())
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu User"));
        modelMapper.map(categoryDTO, existingCategory);
        categoryRepository.save(existingCategory);
    }
}
