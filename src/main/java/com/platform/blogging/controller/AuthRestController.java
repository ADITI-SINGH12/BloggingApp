package com.platform.blogging.controller;

import com.platform.blogging.dto.UserDTO;
import com.platform.blogging.dto.payload.CustomUserDetailsService;
import com.platform.blogging.dto.payload.JwtAuthenticationRequest;
import com.platform.blogging.dto.payload.JwtAuthenticationResponse;
import com.platform.blogging.dto.payload.JwtTokenProvider;
import com.platform.blogging.exceptions.ApiExceptionHandler;
import com.platform.blogging.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Userservice userservice;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> createToken(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest){
        String username = jwtAuthenticationRequest.getUsername();
        String password = jwtAuthenticationRequest.getPassword();
        authenticate(username,password);
        String generatedToken = jwtTokenProvider.generateToken(username);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(generatedToken);
        return new ResponseEntity<>(jwtAuthenticationResponse, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        //need to authenticate
        //authentication manager can authenticate
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        System.out.println(usernamePasswordAuthenticationToken + "Aditi ...");
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            System.out.println("Invalid Username or Password!");
            throw new ApiExceptionHandler("Invalid Username or Password!");

        }
    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerNewUser(@RequestBody UserDTO userDto){
        UserDTO registeredNewUser = this.userservice.createUser(userDto);

        return new ResponseEntity<UserDTO>(registeredNewUser, HttpStatus.CREATED);

    }
}
