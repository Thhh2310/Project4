package com.example.webbanmypham.controller.admin;

import com.example.webbanmypham.dto.CartDTO;
import com.example.webbanmypham.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import output.CartOutput;

import java.util.List;

@RestController
@RequestMapping("admin/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/getAll")
    public CartOutput getAll(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        CartOutput result = new CartOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(cartService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (cartService.totalItem()) / limit));
        model.addAttribute("CartOutput", result);
        return result;
    }
    @GetMapping("/getByStatus/{userID}")
    public CartOutput getByStatus(@PathVariable int userID , @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        try {
            CartOutput result = new CartOutput();
            result.setPage(page);
            Pageable pageable =  PageRequest.of(page - 1, limit);
            result.setListResult(cartService.getByStatus(userID,pageable));
            result.setTotalPage((int) Math.ceil((double) (cartService.totalItem()) / limit));
            model.addAttribute("CartOutput", result);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @GetMapping("/getByUserID/{userID}")
    public CartOutput getByUserID(@PathVariable int userID, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model) {
        CartOutput result = new CartOutput();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(cartService.getByUserID(userID,pageable));
        result.setTotalPage((int) Math.ceil((double) (cartService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @PostMapping("/createCart")
    public ResponseEntity<String> createCart(@RequestBody CartDTO cartDTO) {
        try {
            cartDTO.setStatus(false);
            cartService.createCart(cartDTO);
            return new ResponseEntity<>("more success ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateCart/{cartID}")
    public ResponseEntity<String> updateCart(@PathVariable int cartID, @RequestBody CartDTO cartDTO) {
        try {
            cartDTO.setCartID(cartID);
            cartService.updateCart(cartDTO);
            return new ResponseEntity<>(cartDTO+"Cập nhật giỏ hàng  thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // xóa dữ liệu
    @Transactional
    @DeleteMapping("/deleteCart/{cartID}")
    public ResponseEntity<String> deleteByCartID(@PathVariable  int cartID) {
        try {
            cartService.deleteByCartID(cartID);
            return new ResponseEntity<>("Xóa giỏ hàng thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/deleteCartByUserID/{userID}")
    public ResponseEntity<String> deleteCartByUserID(@PathVariable  int  userID) {
        try {
            cartService.deleteCartByUserID(userID);
            return new ResponseEntity<>("Xóa  thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
