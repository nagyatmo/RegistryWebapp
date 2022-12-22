package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DataHolderController {

    @Autowired
    private DataService service;

    @ModelAttribute("datasList")
    public List<Data> addDatassOfSelectedDataHolderToSession(Model model, SessionStatus sessionStatus, HttpServletRequest request){
        model.addAttribute("dataHolder",(DataHolder) request.getSession().getAttribute("dataHolder"));
        List<Data> datasList = service.getAllData().stream()
                .filter(dat -> dat.getDataHolder().getId().equals(((DataHolder) request.getSession().getAttribute("dataHolder")).getId()))
                .collect(Collectors.toList());
        request.getSession().setAttribute("datasList",datasList);
        return datasList;
    }

    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public String showBetsPage() {
        return "datas";
    }
}
