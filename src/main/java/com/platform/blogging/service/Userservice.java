package com.platform.blogging.service;


import com.platform.blogging.dto.UserDTO;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Userservice {

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO,String username);

    UserDTO getUserById(Integer userId);

    List<UserDTO> getAllUsers();

    void deleteUser(Integer userId);
}
