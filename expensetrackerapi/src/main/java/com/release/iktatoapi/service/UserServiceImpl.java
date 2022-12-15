package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.User;
import com.release.iktatoapi.data.model.UserModel;
import com.release.iktatoapi.data.repository.UserRepository;
import com.release.iktatoapi.service.exception.ItemAlreadyExistsException;
import com.release.iktatoapi.service.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ItemAlreadyExistsException("User is already register with email:"+user.getEmail());
        }
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User read() {
        Long userId = getLoggedInUser().getId();
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for the id:"+userId));
    }

//    @Override
//    public User update(UserModel user) {
//        User existingUser = read();
//        existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
//        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
//        existingUser.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
//        existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
//        return userRepository.save(existingUser);
//    }

    @Override
    public void delete() {
        User existingUser = read();
        userRepository.delete(existingUser);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("User not found for the email "+email));
    }


}
