package com.platform.blogging.dto.payload;

import com.platform.blogging.entity.User;
import com.platform.blogging.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepo userRepository;


    //UserDetailsService methods
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        //load Username of a user from db

        User user = this.userRepository.findByEmail(username);//.orElseThrow(()->new Exception("Resource not found"));

        //this 'loadUserByUsername' method return type is UserDetails,
        //so we need to convert 'user' to 'UserDetails' through implements 'UserDetails' in 'User' Entity Class.
        return user;
    }

}