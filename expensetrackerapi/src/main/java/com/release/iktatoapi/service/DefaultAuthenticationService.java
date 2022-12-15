package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.User;
import com.release.iktatoapi.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DefaultAuthenticationService implements AuthenticationService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
