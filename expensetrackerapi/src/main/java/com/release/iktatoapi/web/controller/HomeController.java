package com.release.iktatoapi.web.controller;


import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.User;
import com.release.iktatoapi.service.AuthenticationService;
import com.release.iktatoapi.service.DataService;
import com.release.iktatoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private DataService dataService;

    @ModelAttribute("loggedInUserRole")
    public String getLoggedInUserRole(){
        System.out.println(userService.getLoggedInUser().getRole());
        return userService.getLoggedInUser().getRole();
    }

    @ModelAttribute("amountIncomeBir")
    public String amountIncomeBir() {
        return String.valueOf(dataService.getAllData().stream()
                .filter(n->n.getIg_category()
                        .equals("Bírósági"))
                .collect(Collectors.toList())
                .stream()
                .filter(n->n.getIsDone()
                        .equals(true))
                        .filter(n->n.getAmount()!=null)
                .mapToInt(n->n.getAmount())
                .sum())+" Ft";
    }

    @ModelAttribute("amountOrderBir")
    public Integer amountOrderBir() {
        return Math.toIntExact(dataService.getAllData()
                .stream()
                .filter(n->n.getIg_category()
                        .equals("Bírósági"))
                .count());
    }
    @ModelAttribute("amountOrderBirDone")
    public Integer amountOrderBirDone() {
        return Math.toIntExact(dataService.getAllData()
                .stream()
                .filter(n->n.getIg_category()
                        .equals("Bírósági"))
                .collect(Collectors.toList())
                .stream()
                .filter(n->n.getIsDone().equals(true))
                .count());
    }

    @ModelAttribute("amountIncomeRen")
    public String amountIncomeRen() {
        return String.valueOf(dataService.getAllData().stream()
                .filter(n->n.getIg_category()
                        .equals("Rendőrségi"))
                .collect(Collectors.toList())
                .stream()
                .filter(n->n.getIsDone()
                        .equals(true))
                .filter(n->n.getAmount()!=null)
                .mapToInt(n->n.getAmount())
                .sum())+" Ft";
    }

    @ModelAttribute("amountOrderRen")
    public Integer amountOrderRen() {
        return Math.toIntExact(dataService.getAllData()
                .stream()
                .filter(n->n.getIg_category()
                        .equals("Rendőrségi"))
                .count());
    }
    @ModelAttribute("amountOrderRenDone")
    public Integer amountOrderRenDone() {
        return Math.toIntExact(dataService.getAllData()
                .stream()
                .filter(n->n.getIg_category()
                        .equals("Rendőrségi"))
                .collect(Collectors.toList())
                .stream()
                .filter(n->n.getIsDone().equals(true))
                .count());
    }

    @ModelAttribute("amountIncomeKoz")
    public String amountIncomeKoz() {
        return String.valueOf(dataService.getAllData().stream()
                .filter(n->n.getIg_category()
                        .equals("Közjegyzői"))
                .collect(Collectors.toList())
                .stream()
                .filter(n->n.getIsDone()
                        .equals(true))
                .filter(n->n.getAmount()!=null)
                .mapToInt(n->n.getAmount())
                .sum())+" Ft";
    }

    @ModelAttribute("amountOrderKoz")
    public Integer amountOrderKoz() {
        return Math.toIntExact(dataService.getAllData()
                .stream()
                .filter(n->n.getIg_category()
                        .equals("Közjegyzői"))
                .count());
    }
    @ModelAttribute("amountOrderKozDone")
    public Integer amountOrderKozDone() {
        return Math.toIntExact(dataService.getAllData()
                .stream()
                .filter(n->n.getIg_category()
                        .equals("Közjegyzői"))
                .collect(Collectors.toList())
                .stream()
                .filter(n->n.getIsDone().equals(true))
                .count());
    }

    @ModelAttribute("amountIncomeHat")
    public String amountIncomeHat() {
        return String.valueOf(dataService.getAllData().stream()
                .filter(n->n.getIg_category()
                        .equals("Hatósági"))
                .collect(Collectors.toList())
                .stream()
                .filter(n->n.getIsDone()
                        .equals(true))
                .filter(n->n.getAmount()!=null)
                .mapToInt(n->n.getAmount())
                .sum())+" Ft";
    }

    @ModelAttribute("amountOrderHat")
    public Integer amountOrderHat() {
        return Math.toIntExact(dataService.getAllData()
                .stream()
                .filter(n->n.getIg_category()
                        .equals("Hatósági"))
                .count());
    }
    @ModelAttribute("amountOrderHatDone")
    public Integer amountOrderHatDone() {
        return Math.toIntExact(dataService.getAllData()
                .stream()
                .filter(n->n.getIg_category()
                        .equals("Hatósági"))
                .collect(Collectors.toList())
                .stream()
                .filter(n->n.getIsDone().equals(true))
                .count());
    }

    @ModelAttribute("amountIncomeMag")
    public String amountIncomeMag() {
        return String.valueOf(dataService.getAllData().stream()
                .filter(n->n.getIg_category()
                        .equals("Magán"))
                .collect(Collectors.toList())
                .stream()
                .filter(n->n.getIsDone()
                        .equals(true))
                .filter(n->n.getAmount()!=null)
                .mapToInt(n->n.getAmount())
                .sum())+" Ft";
    }

    @ModelAttribute("amountOrderMag")
    public Integer amountOrderMag() {
        return Math.toIntExact(dataService.getAllData()
                .stream()
                .filter(n->n.getIg_category()
                        .equals("Magán"))
                .count());
    }
    @ModelAttribute("amountOrderMagDone")
    public Integer amountOrderMagDone() {
        return Math.toIntExact(dataService.getAllData()
                .stream()
                .filter(n->n.getIg_category()
                        .equals("Magán"))
                .collect(Collectors.toList())
                .stream()
                .filter(n->n.getIsDone().equals(true))
                .count());
    }

    @RequestMapping("/home")
    public String showHomePage() {
        return "home";
    }
}
