package com.release.iktatoapi.web.controller;


import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes({"dataHolderAttr", "datasList" })
public class RemoveSelectedDataFromSessionController {

    @Autowired
    private DataService dataService;

    @ModelAttribute("removeDataFromSession")
    public DataHolder setSelectedDataToDone(@RequestParam("dataIndex") int dataIndex, Model model, HttpServletRequest request) {
        request.getSession().removeAttribute("data");
        DataHolder selectedDataHolder = dataService.getDataById((long) dataIndex).getDataHolder();
        model.addAttribute("dataHolderAttr",selectedDataHolder);
        model.addAttribute("datasList",selectedDataHolder.getDataStack());
        return selectedDataHolder;
    }

    @RequestMapping(value ="/removeSelectedDataFromSession", method = RequestMethod.POST)
    public String removeselectedDataHolderFromSession(HttpSession httpSession, WebRequest request) {
        return "redirect:/datas";
    }
}
