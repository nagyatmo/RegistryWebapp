package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.service.DataHolderService;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteSelectedDataController {

        @Autowired
        private DataService dataService;

        @Autowired
        private DataHolderService dataHolderService;

        @ModelAttribute("deleteData")
        public void deleteSelectedData(@RequestParam("dataId") int dataId, HttpServletRequest request) {
            dataService.deleteById((long) dataId);
        }

        @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
        public String selectedDataPost() {
        return "redirect:datas";
    }
}
