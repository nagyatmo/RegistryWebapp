package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;


@Controller
@SessionAttributes({"dataList", "dataHolderAttr"})
public class SelectedDataPostController {

    @Autowired
    private DataService service;

    @ModelAttribute("data")
    public Data addSelectedDataToSession(@RequestParam("dataIndex") int dataIndex, HttpServletRequest request) {
        Data selectedData = service.getDataById((long) dataIndex);
        request.getSession().setAttribute("data",selectedData);
        return selectedData;
    }

    @RequestMapping(value = "/selectedDataPost", method = RequestMethod.POST)
    public String selectedDataHolderPost() {
        return "redirect:selecteddata";
    }
}
