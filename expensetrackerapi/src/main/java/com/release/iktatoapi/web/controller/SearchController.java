package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.service.DataService;
import com.release.iktatoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    @Autowired
    private DataService dataService;

    @Autowired
    private UserService userService;


    @ModelAttribute("datas")
    public List<Data> getAllData(){
        return dataService.getAllData();
    }

    @RequestMapping(path = {"/search"})
    public String home(Data data, Model model, String keyword) {
        if(keyword!=null) {
            List<Data> list = dataService.getByKeyword(keyword).stream().filter(n->n.getUser().equals(userService.getLoggedInUser())).collect(Collectors.toList());
            model.addAttribute("list", list);
        }else {
            List<Data> list = dataService.getAllData().stream().filter(n->n.getUser().equals(userService.getLoggedInUser())).collect(Collectors.toList());
            model.addAttribute("list", list);}
        return "search";
    }
}
