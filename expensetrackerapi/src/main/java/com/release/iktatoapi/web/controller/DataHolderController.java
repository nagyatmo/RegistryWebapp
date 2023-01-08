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
import java.sql.Date;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DataHolderController {

    @Autowired
    private DataService service;

    @ModelAttribute("datasList")
    public List<Data> addDatasOfSelectedDataHolderToSession(Model model, SessionStatus sessionStatus, HttpServletRequest request){
        model.addAttribute("dataHolderAttr",(DataHolder) request.getSession().getAttribute("dataHolderAttr"));
        List<Data> datasList = service.getAllData().stream()
                .filter(dat -> dat.getDataHolder().getId().equals(((DataHolder) request.getSession().getAttribute("dataHolderAttr")).getId()))
                .collect(Collectors.toList());
        request.getSession().setAttribute("datasList",datasList);
        return datasList;
    }

    @RequestMapping(value = "/datas", method = RequestMethod.GET)
    public String showBetsPage() {
        return "datas";
    }
}
