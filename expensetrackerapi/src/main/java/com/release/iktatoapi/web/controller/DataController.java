package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.data.entity.LongIdModel;
import com.release.iktatoapi.service.DataHolderService;
import com.release.iktatoapi.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Controller
public class DataController {

    @Autowired
    private DataHolderService dataHolderService;

    @Autowired
    private DataService dataService;

    private final String UPLOAD_DIR = "./uploads/";

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

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file , @ModelAttribute Data dataEditInfo, RedirectAttributes redirectAttributes,
                             HttpServletRequest request) {
        Data currentSessionData = addSelectedDataToSession(request);
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Adjon hozzá fájlt a feltöltéshez.");
            return "redirect:selecteddata";
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            dataService.store(file, dataEditInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("message", "Sikeresen feltöltött fájl: " + fileName + '!');

        return "redirect:selecteddata";
    }

    @GetMapping("/download")
    public String downloadFile(@Param("id") Long id, HttpServletResponse response) throws IOException {
        Optional<Data> temp = Optional.ofNullable(dataService.getDataById(id));
        if(temp.isPresent()) {
            Data data = temp.get();
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = "+data.getUploadedFile().getName();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(data.getUploadedFile().getData());
            outputStream.close();
        }
        return "redirect:selecteddata";
    }

    @RequestMapping(value = "/selecteddata", method = RequestMethod.GET)
    public String showSelectedDataPage(Model model, HttpServletRequest request) {
        model.addAttribute("dataEditInfo",addSelectedDataToSession(request));
        model.addAttribute("datasList",dataHolderService.getAllDataHolder());
        model.addAttribute("idDataHolderValue", new LongIdModel());
        return "selecteddata";
    }

}
