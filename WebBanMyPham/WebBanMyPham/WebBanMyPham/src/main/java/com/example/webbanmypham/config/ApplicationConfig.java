package com.example.webbanmypham.config;

import mapper.Opject.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public UserMapper userMapper(ModelMapper modelMapper){
        return new UserMapper(modelMapper );
    }
    @Bean
    public CartMapper cartMapper(ModelMapper modelMapper){
        return new CartMapper(modelMapper );
    }
    @Bean
    public CategoryMapper categoryMapper(ModelMapper modelMapper){
        return new CategoryMapper(modelMapper );
    }
    @Bean
    public OrderMapper orderMapper(ModelMapper modelMapper){
        return new OrderMapper(modelMapper );
    }
    @Bean
    public OrderDetailMapper orderDetailMapper(ModelMapper modelMapper){
        return new OrderDetailMapper(modelMapper );
    }
    @Bean
    public ProductMapper productMapper(ModelMapper modelMapper){
        return new ProductMapper(modelMapper );
    }
}
