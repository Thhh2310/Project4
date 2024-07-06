package com.example.webbanmypham.controller.admin;

import com.example.webbanmypham.dto.ProductDTO;
import com.example.webbanmypham.dto.UserDTO;
import com.example.webbanmypham.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import output.ProductOutput;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/productList")
    public ProductOutput getAll(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        ProductOutput result = new ProductOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(productService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (productService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }

    @GetMapping("/productByCategoryIDList/{categoryID}")
    public ProductOutput getByCategoryID(@PathVariable int categoryID, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        ProductOutput result = new ProductOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(productService.getByCategoryID(categoryID,pageable));
        result.setTotalPage((int) Math.ceil((double) (productService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }

    @GetMapping("/productByNameList/{productName}")
    public ProductOutput getByProductName(@PathVariable String productName,@RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        ProductOutput result = new ProductOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(productService.getByProductName(productName,pageable));
        result.setTotalPage((int) Math.ceil((double) (productService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @GetMapping("/productByID/{productID}")
    public ResponseEntity<?> getByProductID(@PathVariable int productID) {
        try {
            ProductDTO productsDTO = productService.getByProductID(productID);

            return new ResponseEntity<>(productsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<UserDTO>) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createProduct")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productsDTO) {
        try {
            productService.createProduct(productsDTO);
            return new ResponseEntity<>("more success ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateProduct/{productID}")
    public ResponseEntity<String> updateProduct(@PathVariable int productID, @RequestBody ProductDTO productDTO) {
        try {
            productDTO.setProductID(productID);
            productService.updateProduct(productDTO);
            return new ResponseEntity<>(productDTO+"Cập nhật sản phẩm thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // xóa dữ liệu
    @Transactional
    @DeleteMapping("/deleteProduct/{productID}")
    public ResponseEntity<String> deleteProduct(@PathVariable  int productID) {
        try {
            productService.deleteProduct(productID);
            return new ResponseEntity<>("Xóa sản phẩm thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
