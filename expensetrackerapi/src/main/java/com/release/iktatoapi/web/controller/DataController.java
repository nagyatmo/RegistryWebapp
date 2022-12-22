package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.service.DataHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class DataController {

    @Autowired
    private DataHolderService dataHolderService;

    @ModelAttribute("iktNumList")
    public List<DataHolder> listOfIktNums(){
        return dataHolderService.getAllDataHolder();
    }

    @ModelAttribute("data")
    public Data addSelectedDataHToSession(HttpServletRequest request){
        Data returnData = (Data) request.getSession().getAttribute("data");
        return returnData;
    }


    @RequestMapping(value = "/selecteddata", method = RequestMethod.GET)
    public String showSelectedDataPage() {
        return "selecteddata";
    }

}
