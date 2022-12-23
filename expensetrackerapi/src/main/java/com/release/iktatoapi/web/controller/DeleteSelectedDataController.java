package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.service.DataHolderService;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Controller
//@SessionAttributes("dataHolderAttr")
public class DeleteSelectedDataController {

        @Autowired
        private DataService dataService;

        @Autowired
        private DataHolderService dataHolderService;

        @ModelAttribute("deleteData")
        public void deleteSelectedData(@RequestParam("dataId") int dataId, Model model, HttpServletRequest request) {
                DataHolder dataHolderToUpdate = (DataHolder) request.getSession().getAttribute("dataHolderAttr");
                //model.addAttribute("dataHolderAttr",(DataHolder) request.getSession().getAttribute("dataHolderAttr"));
                dataService.deleteById((long) dataId);
                dataHolderService.updateIktNum(dataHolderToUpdate);
        }

        @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
        public String selectedDataPost(Model model, HttpServletRequest request) {


        return "redirect:datas";
    }
}
