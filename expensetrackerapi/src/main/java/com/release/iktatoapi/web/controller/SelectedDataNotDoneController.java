package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"dataHolder", "datasList"})
public class SelectedDataNotDoneController {

    @Autowired
    private DataService dataService;



    @ModelAttribute("setNotDoneData")
    public Data setSelectedDataToDone(@RequestParam("dataIndex") int dataIndex, Model model) {
        Data selectedData = dataService.getAllData().get(dataIndex - 1);
        model.addAttribute("dataHolder",selectedData.getDataHolder());
        model.addAttribute("datasList",selectedData.getDataHolder().getDataStack());
        selectedData.setIsDone(false);
        dataService.updateDataDetails(selectedData.getId(),selectedData);
        return selectedData;
    }

    @RequestMapping(value = "/setNotDone", method = RequestMethod.POST)
    public String selectedDataPost() {
        return "redirect:datas";
    }
}
