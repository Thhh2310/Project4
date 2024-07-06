package com.example.webbanmypham.service.IMPL;

import com.example.webbanmypham.dto.CartDTO;
import com.example.webbanmypham.entity.CartEntity;
import com.example.webbanmypham.entity.ProductEntity;
import com.example.webbanmypham.entity.UserEntity;
import com.example.webbanmypham.repository.CartRepository;
import com.example.webbanmypham.repository.ProductRepository;
import com.example.webbanmypham.repository.UserRepository;
import com.example.webbanmypham.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import mapper.Opject.CartMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartServiceIMPL implements CartService {
    @Autowired
    private CartRepository cartRepository;
    private ModelMapper modelMapper;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CartMapper cartMapper;

    public CartServiceIMPL(CartRepository cartRepository, ModelMapper modelMapper, ProductRepository productRepository, UserRepository userRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public List<CartDTO> getAll(Pageable pageable) {
        List<CartDTO> results = new ArrayList<>();
        List<CartEntity> cartEntities = cartRepository.findAll(pageable);
        for (CartEntity item: cartEntities
        ) {
            CartDTO shoppingCartDTO = cartMapper.maptoDTO(item);
            results.add(shoppingCartDTO);
        }
        return results;
    }

//    @Override
//    public CartDTO getByCartID(int cartID) {
//        try {
//            CartEntity cart = cartRepository.findByCartID(cartID)
//                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + cartID));
//
//            return modelMapper.map(cart, CartDTO.class);
//        } catch (EntityNotFoundException ex) {
//            throw ex;
//        } catch (Exception e) {
//            throw new RuntimeException("An error occurred while fetching data by ID", e);
//        }
//    }
    @Override
    public int totalItem() {
        return (int) cartRepository.count();
    }

    @Override
    public CartDTO getByCartID(int cartID) {
        try {
            CartEntity cart = cartRepository.findByCartID(cartID)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + cartID));
            return cartMapper.maptoDTO(cart);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }
    @Override
    public List<CartDTO> getByStatus( int userID, Pageable pageable) {
        List<CartDTO> results = new ArrayList<>();
        UserEntity user = userRepository.findByUserID(userID).orElse(null);
        List<CartEntity> shoppingCartUserList = cartRepository.findByUserID(user,pageable);
        for (CartEntity item: shoppingCartUserList
        ) {
            CartDTO cartDTO = null;
            if (item.getStatus() == true){
                cartDTO  = cartMapper.maptoDTO(item);
                results.add(cartDTO);
            }


        }
        return results;
    }
    @Override
    public List<CartDTO> getByProductID(int productID, Pageable pageable) {
        List<CartDTO> results = new ArrayList<>();
        List<CartEntity> cartEntities = cartRepository.findByProductID(productID,pageable);
        for (CartEntity item: cartEntities
        ) {
            CartDTO shoppingCartDTO = cartMapper.maptoDTO(item);
            results.add(shoppingCartDTO);
        }
        return results;
    }
    @Override
    public List<CartDTO> getByUserID(int userID, Pageable pageable) {
        List<CartDTO> results = new ArrayList<>();
        UserEntity user = userRepository.findByUserID(userID).orElse(null);
        List<CartEntity> cartUserList = cartRepository.findByUserID(user,pageable);
        for (CartEntity item: cartUserList
        ) {
            CartDTO cartDTO = null;
            if (item.getStatus() == true){
                cartDTO  = cartMapper.maptoDTO(item);
                results.add(cartDTO);
            }


        }
        return results;
    }

    @Override
    public void deleteCartByUserID(int userID) {
        Optional<UserEntity> optionalUser = userRepository.findByUserID(userID);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            List<CartEntity> cart = cartRepository.findByUserID(user);
            for (CartEntity item : cart) {
                cartRepository.deleteByCartID(item.getCartID());
            }
        } else {
            System.out.println("User not found with ID: " + userID);
        }
    }


    @Override
    public void deleteByCartID(int cartID) {
        cartRepository.deleteByCartID(cartID);
    }

    @Override
    public void createCart(CartDTO cartDTO) {
        if ( cartDTO != null) {
            CartEntity cart = cartMapper.maptoEntity(cartDTO);
            UserEntity user = userRepository.findByUserID(cartDTO.getUserID()).orElse(null);
            ProductEntity product  = productRepository.findByProductID(cartDTO.getProductID()).orElse(null);
            if (cart != null) {
                int quantity = product.getQuantity() - cart.getQuantity();
                if (quantity >= 0){
                    product.setQuantity(quantity);
                    productRepository.save(product);
                    cart.setUserID(user);
                    cart.setProductID(product);
                    cart.setStatus(false);
                    cartRepository.saveAndFlush(cart);
                }else {
                    throw new RuntimeException("sản phẩm này đã hết ");
                }
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }

    @Override
    public void updateCart(CartDTO cartDTO) {
        CartEntity existingCart  = cartRepository.findByCartID(cartDTO.getCartID())
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu User"));
        modelMapper.map(cartDTO, existingCart);
        cartRepository.saveAndFlush(existingCart);
    }
}
