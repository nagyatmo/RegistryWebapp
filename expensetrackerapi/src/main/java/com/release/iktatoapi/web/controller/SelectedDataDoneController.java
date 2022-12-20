package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SelectedDataDoneController {
    @Autowired
    private DataService dataService;



    @ModelAttribute("setDoneData")
    public Data setSelectedDataToDone(@RequestParam("dataIndex") int dataIndex) {
        Data selectedData = dataService.getAllData().get(dataIndex - 1);
        selectedData.setIsDone(true);
        dataService.updateDataDetails(selectedData.getId(),selectedData);
        return selectedData;
    }

    @RequestMapping(value = "/setDone", method = RequestMethod.POST)
    public String selectedDataPost() {
        return "redirect:search";
    }
}
