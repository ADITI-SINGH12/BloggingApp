package com.platform.blogging.controller;

import com.platform.blogging.dto.UserDTO;
import com.platform.blogging.dto.payload.JwtAuthenticationResponse;
import com.platform.blogging.dto.payload.JwtTokenProvider;
import com.platform.blogging.entity.User;
import com.platform.blogging.service.Userservice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private Userservice userservice;;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO saveduserDTO = userservice.createUser(userDTO);
      //  System.out.println("jwtToken" +token);
     return new ResponseEntity<>(saveduserDTO, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listOfAllUser(){
       List<UserDTO> userdtoLIST = userservice.getAllUsers();
       // List<User> userList = modelMapper.map(userdtoLIST, new TypeToken<List<User>>() {}.getType());
        return new ResponseEntity<>(userdtoLIST, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto,HttpServletRequest request){
        System.out.println("Token fetched "+request.getHeader("Authorization"));
        String header = request.getHeader("Authorization");
        String token = header.startsWith("Bearer ") ? header.substring(7) : header;
        String username = jwtTokenProvider.getUsernameFromToken(token);
        System.out.println("username fetched "+username);
        UserDTO updatedUser = userservice.updateUser(userDto,username);
        //return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);//or
        return  ResponseEntity.ok(updatedUser);
    }
}
