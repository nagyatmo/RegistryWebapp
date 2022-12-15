//package com.release.iktatoapi.web.controller;
//
//import com.release.iktatoapi.data.entity.Data;
//import com.release.iktatoapi.service.DataService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.data.domain.Pageable;
//
//import javax.validation.Valid;
//import java.sql.Date;
//import java.util.List;
//
//@RestController
//public class DataController {
//
//    @Autowired
//    private DataService dataService;
//
//    @GetMapping("/datas")
//    public List<Data> getAllData(){
//        return dataService.getAllData();
//    }
//
//    @GetMapping("/datas/{id}")
//    public Data getDataById(@PathVariable("id") Long id){
//        return dataService.getDataById(id);
//    }
//
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    @DeleteMapping("/datas")
//    public void deleteDataById(@RequestParam("id")Long id){
//        dataService.deleteById(id);
//    }
//
//    @ResponseStatus(value = HttpStatus.CREATED)
//    @PostMapping("/datas")
//    public Data saveDataDetails(@Valid @RequestBody Data data){
//        return dataService.saveData(data);
//    }
//
//    @PutMapping("/datas/{id}")
//    public Data updateDataDetails(@RequestBody Data data, @PathVariable Long id){
//        return dataService.updateDataDetails(id, data);
//    }
//
//    @GetMapping("/datas/hsznum")
//    public List<Data> getAllDataByHszNum(@RequestParam String hszNum, Pageable page){
//        return dataService.readByHszNum(hszNum,page);
//    }
////
////    @GetMapping("/expenses/date")
////    public List<Data> getAllExpensesByDate(@RequestParam(required = false) Date startDate,
////                                           @RequestParam(required = false) Date endDate,
////                                           Pageable page){
////        return dataService.readByDate(startDate,endDate,page);
////    }
//}
