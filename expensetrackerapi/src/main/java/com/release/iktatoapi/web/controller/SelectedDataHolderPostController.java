package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.service.DataHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class SelectedDataHolderPostController {

    @Autowired
    private DataHolderService service;

    @ModelAttribute("dataHolderAttr")
    public DataHolder addSelectedDataHolderToSession(@RequestParam("dataHolderIndex") int dataHolderIndex, HttpServletRequest request) {
        DataHolder selectedDataHolder = service.getAllDataHolder().get(dataHolderIndex-1);
        request.getSession().setAttribute("dataHolderAttr",selectedDataHolder);
        return selectedDataHolder;
    }

    @RequestMapping(value = "/selectedDataHolderPost", method = RequestMethod.POST)
    public String selectedDataHolderPost() {
        return "redirect:datas";
    }
}
