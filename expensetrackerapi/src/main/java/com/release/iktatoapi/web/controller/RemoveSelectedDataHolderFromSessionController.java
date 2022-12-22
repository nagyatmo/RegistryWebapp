package com.release.iktatoapi.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;


@Controller
public class RemoveSelectedDataHolderFromSessionController {

    @RequestMapping(value ="/removeSelectedDataHolderFromSession", method = RequestMethod.POST)
    public String removeselectedDataHolderFromSession(SessionStatus sessionStatus, HttpServletRequest request) {
        request.getSession().removeAttribute("dataHolder");
        request.getSession().removeAttribute("datasList");
        return "redirect:/search2";
    }

}
