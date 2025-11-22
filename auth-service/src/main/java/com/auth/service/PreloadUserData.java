package com.auth.service;



import com.auth.entities.UserEntity;
import com.auth.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class  PreloadUserData {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    void loadUserData() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("1232");
        userEntity.setPassWord(passwordEncoder.encode("365656"));
        System.out.println("password : " + userEntity.getPassWord());
        userRepository.save(userEntity);
    }


}