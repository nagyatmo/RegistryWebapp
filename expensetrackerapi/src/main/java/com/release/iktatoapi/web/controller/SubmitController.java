package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.service.DataHolderService;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class SubmitController {

    @Autowired
    private DataService dataService;

    @Autowired
    private DataHolderService dataHolderService;


    @PostMapping("/generateDataHolder")
    public String generateDataHolder(){
        DataHolder newDataHolder = new DataHolder();
        dataHolderService.saveDataHolder(newDataHolder);
        return "redirect:/submit";
    }
    @PostMapping("/deleteDataHolder")
    public String deleteDataHolder(Model model, @ModelAttribute Data dataInfo){
        if(dataInfo.getDataHolder().getDataStack().size()==0) {
            dataHolderService.deleteById(dataInfo.getDataHolder().getId());
        }
        return "redirect:/submit";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String getSubmit(Model model) {
        model.addAttribute("dataInfo", new Data());
        model.addAttribute("dataList", dataHolderService.getAllDataHolder());
        return "submit";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String saveDataDetails(Model model, @ModelAttribute Data dataInfo, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("success","Iktatás sikeresen rögzítve!");
        dataService.saveData(dataInfo);
        return "redirect:/submit";
    }
}
