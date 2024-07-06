package com.example.webbanmypham.controller.admin;

import com.example.webbanmypham.dto.CategoryDTO;
import com.example.webbanmypham.dto.UserDTO;
import com.example.webbanmypham.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import output.CategoryOutput;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categoryList")
    public CategoryOutput getAll(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        CategoryOutput result = new CategoryOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(categoryService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (categoryService.totalItem()) / limit));
        model.addAttribute("CategoryOutput", result);
        return result;
    }
    @GetMapping("/categoryByID/{categoryID}")
    public ResponseEntity<?> getByCategoryID(@PathVariable int categoryID) {
        try {
            CategoryDTO categoryDTO = categoryService.getByCategoryID(categoryID);

            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<UserDTO>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/categoryByCategoryNameList")
    public CategoryOutput getByCategoryName( @PathVariable String categoryName,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        CategoryOutput result = new CategoryOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(categoryService.getByCategoryName(categoryName,pageable));
        result.setTotalPage((int) Math.ceil((double) (categoryService.totalItem()) / limit));
        model.addAttribute("CategoryOutput", result);
        return result;
    }

    @PostMapping("/createCategory")
    public ResponseEntity<String> createOrderDetail(@RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.createCategory(categoryDTO);
            return new ResponseEntity<>("more success " + categoryDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCategory/{categoryID}")
    public ResponseEntity<String> updateCategory(@PathVariable int categoryID, @RequestBody CategoryDTO categoryDTO) {
        try {
            categoryDTO.setCategoryID(categoryID);
            categoryService.updateCategory(categoryDTO);
            return new ResponseEntity<>(categoryDTO+"Cập nhật danh mục thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping("/deleteCategory/{categoryID}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryID) {
        try {
            categoryService.deleteCategory(categoryID);
            return new ResponseEntity<>("Xóa Ảnh thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

