package com.example.webbanmypham.service.IMPL;
import com.example.webbanmypham.dto.ProductDTO;
import com.example.webbanmypham.entity.CategoryEntity;
import com.example.webbanmypham.entity.ProductEntity;
import com.example.webbanmypham.repository.CategoryRepository;
import com.example.webbanmypham.repository.ProductRepository;
import com.example.webbanmypham.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import mapper.Opject.ProductMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

public class ProductServiceIMPL implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private ProductMapper productMapper;
    private CategoryRepository categoryRepository;
    public ProductServiceIMPL(ProductRepository productsRepository, ModelMapper modelMapper, ProductMapper productMapper, CategoryRepository categoryRepository) {
        this.productRepository = productsRepository;
        this.modelMapper = modelMapper;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;

    }
    @Override
    public List<ProductDTO> getAll(Pageable pageable) {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> productsEntities = productRepository.findAll(pageable).getContent();
        for (ProductEntity item : productsEntities
        ) {
            ProductDTO DTO = productMapper.maptoDTO(item);
            results.add(DTO);
        }
        return results;
    }

//    @Override
//    public ProductDTO getByProductID(int productID) {
//        try {
//            ProductEntity product = productRepository.findByProductID(productID)
//                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + productID));
//            return modelMapper.map(product, ProductDTO.class);
//        } catch (EntityNotFoundException ex) {
//            throw ex;
//        } catch (Exception e) {
//            throw new RuntimeException("An error occurred while fetching data by ID", e);
//        }
//    }
    @Override
    public int totalItem() {
        return (int) productRepository.count();
    }
    @Override
    public ProductDTO getByProductID(int productID) {
        try {
            ProductEntity products = productRepository.findByProductID(productID)
                    .orElseThrow(() -> new EntityNotFoundException("Data not found with ID: " + productID));
            return productMapper.maptoDTO(products);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }
    @Override
    public List<ProductDTO> getByProductName(String productName, Pageable pageable) {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> productsEntities = productRepository.findByProductName(productName, pageable);
        for (ProductEntity item : productsEntities
        ) {
            ProductDTO DTO = productMapper.maptoDTO(item);
            results.add(DTO);
        }
        return results;
    }
    @Override
    public List<ProductDTO> getByProductPrice(int productPrice, Pageable pageable) {
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> productsEntities = productRepository.findByProductPrice(productPrice, pageable);
        for (ProductEntity item : productsEntities
        ) {
            ProductDTO DTO = productMapper.maptoDTO(item);
            results.add(DTO);
        }
        return results;
    }
    @Override
    public List<ProductDTO> getByCategoryID(int categoryID, Pageable pageable) {
        List<ProductDTO> results = new ArrayList<>();
        CategoryEntity categoryEntity = categoryRepository.findByCategoryID(categoryID).orElse(null);
        List<ProductEntity> productsEntities = productRepository.findByCategoryID(categoryEntity, pageable);

        for (ProductEntity item : productsEntities
        ) {
            ProductDTO DTO = productMapper.maptoDTO(item);
            results.add(DTO);
        }
        return results;
    }
    @Override
    public void deleteProduct(int productID) {
        productRepository.deleteByProductID(productID);
    }

    @Override
    public ResponseEntity<?>  createProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return ResponseEntity.badRequest().body("ProductDTO cannot be null");
        }

        try {
            ProductEntity product = productMapper.maptoEntity(productDTO);
            if (product == null) {
                throw new RuntimeException("Cannot map ProductDTO to ProductEntity");
            }

            CategoryEntity category = categoryRepository.findByCategoryID(productDTO.getCategoryID().getCategoryID()).orElse(null);
            if (category == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
            }

            List<ProductEntity> productEntityList = productRepository.findByProductName(productDTO.getProductName());
            if (productEntityList.isEmpty()) {
                product.setCategoryID(category);
                productRepository.save(product);
                return ResponseEntity.status(HttpStatus.CREATED).body(product);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Product with the same name already exists");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @Override
    public void updateProduct(ProductDTO productDTO) {
        ProductEntity existingProducts = productRepository.findByProductID(productDTO.getProductID())
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu User"));
        modelMapper.map(productDTO, existingProducts);
        productRepository.save(existingProducts);
    }
}
