package com.auth.service;

import com.auth.entities.UserEntity;
import com.auth.repository.UserRepository;
import com.auth.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity =  userRepository.findByUserName(username);
        User user = null;
        if(userEntity != null) {
           user = User.builder().userName(userEntity.getUserName()).passWord(userEntity.getPassWord()).build();
        }
        return user;
    }
}
