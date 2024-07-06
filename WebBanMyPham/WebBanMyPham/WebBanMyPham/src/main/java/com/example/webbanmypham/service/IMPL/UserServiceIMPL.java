package com.example.webbanmypham.service.IMPL;

import com.example.webbanmypham.dto.UserDTO;
import com.example.webbanmypham.entity.UserEntity;
import com.example.webbanmypham.repository.UserRepository;
import com.example.webbanmypham.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import mapper.Opject.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    private UserMapper userMapper;
    public UserServiceIMPL(UserRepository userRepository, UserMapper userMapper, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> getAll(Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll(pageable).getContent();
        for (UserEntity item: userEntities
        ) {
            UserDTO userDTO = modelMapper.map(item,UserDTO.class);
            results.add(userDTO);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) userRepository.count();
    }

    @Override
    public List<UserDTO> getByUserName(String userName, Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findByUserName(userName, pageable);
        for (UserEntity item: userEntities
        ) {
            UserDTO userDTO = modelMapper.map(item,UserDTO.class);
            results.add(userDTO);
        }
        return results;
    }

    @Override
    public List<UserDTO> getByEmail(String email) {
        try {
            List<UserEntity> user  = userRepository.findByEmail(email);
            return user.stream()
                    .map(userMapper::maptoDTO)
                    .collect(Collectors.toList());
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public List<UserDTO> getByIsAdmin(int userID, Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        UserEntity userEntity  = userRepository.findByIsAdmin(userID).orElse(null);
        List<UserEntity> userEntities = userRepository.finByIsAdmin(userEntity, pageable);
        for (UserEntity item: userEntities
        ) {
            UserDTO userDTO = userMapper.maptoDTO(item);
            results.add(userDTO);
        }
        return results;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        if ( userDTO != null) {
            UserEntity user = userMapper.maptoEntity(userDTO);
            UserEntity userEntity = userRepository.findByIsAdmin(userDTO.getIsAdmin()).orElse(null);
            if (user != null) {
                user.setIsAdmin(String.valueOf(userEntity));
                userRepository.save(user);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        UserEntity existingUser  = userRepository.findByUserID(userDTO.getUserID())
                .orElseThrow(() -> new RuntimeException("Khong tim thay du lieu User"));
        modelMapper.map(userDTO, existingUser);
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(int userID ) {
        userRepository.deleteByUserID(userID);
    }

    @Override
    public UserDTO getByUserID(int userID) {
        return null;
    }
}
