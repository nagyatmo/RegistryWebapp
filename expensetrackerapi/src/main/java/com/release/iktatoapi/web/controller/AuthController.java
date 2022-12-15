package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.JwtResponse;
import com.release.iktatoapi.data.entity.User;
import com.release.iktatoapi.data.model.UserModel;
import com.release.iktatoapi.web.security.CustomUserDetailsService;
import com.release.iktatoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @PostMapping("/login")
//    public String login(@RequestBody UserModel authModel, HttpSession httpSession) throws Exception {
//
//        authenticate(authModel.getEmail(), authModel.getPassword());
//
//        //we need to generate the jwt token
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authModel.getEmail());
//
//        final String token = jwtTokenUtil.generateToken(userDetails);
//        httpSession.setAttribute("jwtToken",token);
//
//        return "home";
//    }



    private void authenticate(String email, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials");
        }

    }

//    @PostMapping("/register")
//    public ResponseEntity<User> save(@Valid @RequestBody UserModel user) {
//        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
//    }
}




