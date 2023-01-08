package com.release.iktatoapi.web.controller;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.data.repository.DataHolderRepository;
import com.release.iktatoapi.data.repository.DataRepository;
import com.release.iktatoapi.service.DataService;
import com.release.iktatoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"dataHolder", "dataList"})
public class SearchController {

    @Autowired
    private DataHolderRepository dataHolderRepository;

    @Autowired
    private DataService dataService;

    @Autowired
    private UserService userService;

    @Autowired
    private DataRepository dataRepository;


    @GetMapping("/search2")
    public String getAll(Model model, @RequestParam(defaultValue ="1") int page, @RequestParam(required = false) String keyword,
                         @RequestParam(defaultValue="8") int size){
        try {
            List<DataHolder> dataHolderList = new ArrayList<DataHolder>();
            Pageable paging = PageRequest.of(page - 1, size);
            Page<DataHolder> dataHolders;
            List<Data> dataList = new ArrayList<>();
            Page<Data> datas;

            if (keyword == null) {
                dataHolders = dataHolderRepository.findAll(paging);
                dataHolderList = dataHolders.getContent();
                model.addAttribute("dataHolderList", dataHolderList);
                model.addAttribute("currentPage", dataHolders.getNumber() + 1);
                model.addAttribute("totalItems", dataHolders.getTotalElements());
                model.addAttribute("totalPages", dataHolders.getTotalPages());
            } else {
                datas=dataService.getByKeyword(keyword, paging);
                dataList = datas.getContent();
                model.addAttribute("dataList", dataList);
                model.addAttribute("currentPage", datas.getNumber() + 1);
                model.addAttribute("totalItems", datas.getTotalElements());
                model.addAttribute("totalPages", datas.getTotalPages());
            }

            dataService.getAllData().stream().forEach(n->dataService.setToUrgentIfUrgent(n.getId()));
            model.addAttribute("keyword", keyword);
            model.addAttribute("pageSize", size);
        }catch (Exception e){
            model.addAttribute("message",e.getMessage());
        }
        return "search2";
    }

    @RequestMapping(path = {"/searchReset"})
    public String searchReset() {
        return "search2";
    }

}
