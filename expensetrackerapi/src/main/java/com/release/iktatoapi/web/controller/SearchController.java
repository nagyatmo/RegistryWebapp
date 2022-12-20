package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.service.DataHolderService;
import com.release.iktatoapi.service.DataService;
import com.release.iktatoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    @Autowired
    private DataService dataService;

    @Autowired
    private UserService userService;

    @Autowired
    private DataHolderService dataHolderService;


    @ModelAttribute("datas")
    public List<Data> getAllData(){
        return dataService.getAllData();
    }

//    @ModelAttribute("maindatas")
//    public List<String> getAllMainDatas(){
//        return dataHolderService.getAllDataHolder().stream().map(n->n.getIktNum()).collect(Collectors.toList());
//    }

    @ModelAttribute("maindatas")
    public List<DataHolder> getAllMainDatas(){
        return dataHolderService.getAllDataHolder();
    }


    @RequestMapping(path = {"/search"})
    public String home(Data data, Model model, String keyword) {
        if(keyword!=null) {
            List<Data> list = dataService.getByKeyword(keyword).stream().filter(n -> n.getUser().equals(userService.getLoggedInUser())).collect(Collectors.toList());
            model.addAttribute("list", list);
        }
        return "search";
    }

    @RequestMapping(path = {"/searchReset"})
    public String searchReset() {
        return "search";
    }
}
