package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.data.entity.LongIdModel;
import com.release.iktatoapi.service.DataHolderService;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class DataController {

    @Autowired
    private DataHolderService dataHolderService;

    @Autowired
    private DataService dataService;


    @ModelAttribute("data")
    private Data addSelectedDataToSession(HttpServletRequest request){
        Data returnData = (Data) request.getSession().getAttribute("data");
        return returnData;
    }


    @RequestMapping(value = "/submitEdit", method = RequestMethod.POST)
    public String saveDataDetails(@ModelAttribute Data dataEditInfo, RedirectAttributes redirectAttributes,
                                  HttpServletRequest request, @ModelAttribute(name = "idDataHolderValue") LongIdModel idDataHolderValue){
        redirectAttributes.addFlashAttribute("success","Módosítás sikeresen mentve!");
        Data currentSessionData = addSelectedDataToSession(request);
        DataHolder oldDataHolder =currentSessionData.getDataHolder();
        DataHolder newDataHolder = dataHolderService.getDataHolderById(Long.valueOf(idDataHolderValue.getIdValue()));
        if(newDataHolder!=oldDataHolder){
            dataEditInfo.setDataHolder(newDataHolder);
            dataService.updateDataDetails(currentSessionData.getId(),dataEditInfo);
            dataHolderService.updateIktNum(oldDataHolder);
            dataHolderService.updateIktNum(newDataHolder);
            dataService.updateDataDetails(currentSessionData.getId(),dataEditInfo);
        }else {
            dataService.updateDataDetails(currentSessionData.getId(), dataEditInfo);
        }
        return "redirect:/selecteddata";
    }


    @RequestMapping(value = "/selecteddata", method = RequestMethod.GET)
    public String showSelectedDataPage(Model model, HttpServletRequest request) {
        model.addAttribute("dataEditInfo",addSelectedDataToSession(request));
        model.addAttribute("datasList",dataHolderService.getAllDataHolder());
        model.addAttribute("idDataHolderValue", new LongIdModel());
        return "selecteddata";
    }

}
