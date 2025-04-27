package com.platform.blogging.service.impl;

import com.platform.blogging.dto.UserDTO;
import com.platform.blogging.dto.payload.JwtTokenProvider;
import com.platform.blogging.entity.User;

import com.platform.blogging.repository.UserRepo;
import com.platform.blogging.service.Userservice;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userserviceimpl implements Userservice {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
   private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Convert DTO to Entity
        User user = modelMapper.map(userDTO, User.class);
        //encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save User
        User savedUser = userRepo.save(user);

        // Convert Entity to DTO and return
        return modelMapper.map(user,UserDTO.class);//jwtTokenProvider.generateToken(user.getEmail());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO,String username) {
        User user = userRepo.findByEmail(username);
        user.setName(userDTO.getName());
        user.setAbout(userDTO.getAbout());
       User updatedUser =  userRepo.save(user);
        return modelMapper.map(updatedUser,UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
         List<User> userList = userRepo.findAll();

        return  modelMapper.map(userList, new TypeToken<List<UserDTO>>() {}.getType());
    }

    @Override
    public void deleteUser(Integer userId) {

    }

}

