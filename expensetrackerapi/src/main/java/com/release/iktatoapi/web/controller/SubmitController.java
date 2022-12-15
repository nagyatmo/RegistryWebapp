package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SubmitController {

    @Autowired
    private DataService dataService;


    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String getSubmit(Model model) {
        model.addAttribute("dataInfo", new Data());
        return "submit";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String saveDataDetails(Model model, @ModelAttribute Data dataInfo){
        Data newdata = dataService.saveData(dataInfo);
        return "redirect:/submit";
    }
}
