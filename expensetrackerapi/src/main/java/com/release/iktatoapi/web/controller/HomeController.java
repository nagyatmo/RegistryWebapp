package com.release.iktatoapi.web.controller;


import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.User;
import com.release.iktatoapi.service.AuthenticationService;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private DataService dataService;


    @ModelAttribute("user")
    public User userInfo() {
        return authenticationService.findUserByEmail(getUserDetails());
    }

    @ModelAttribute("amountIncome")
    public Integer amountIncome() {
        return dataService.getAllData().stream().mapToInt(n->n.getAmount()).sum();
    }

    @ModelAttribute("amountOrder")
    public Integer amountOrder() {
        return Math.toIntExact(dataService.getAllData().stream().count());
    }

    private String getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if(principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @RequestMapping("/home")
    public String showHomePage() {
        return "home";
    }
}
