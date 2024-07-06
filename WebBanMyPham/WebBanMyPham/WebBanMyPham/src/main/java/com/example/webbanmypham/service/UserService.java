package com.example.webbanmypham.service;


import com.example.webbanmypham.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll(Pageable pageable);
    int totalItem();
    List<UserDTO> getByUserName(String userName, Pageable pageable);
    List<UserDTO> getByEmail(String email);
    List<UserDTO> getByIsAdmin(int userID, Pageable pageable);
    void createUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(int userDTO);

    UserDTO getByUserID(int userID);
}
