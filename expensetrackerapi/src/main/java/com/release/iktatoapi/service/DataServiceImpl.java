package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.data.repository.DataRepository;
import com.release.iktatoapi.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private UserService userService;

    @Autowired
    private DataRepository dataRepo;

    @Autowired
    private DataHolderService dataHolderService;

    @Override
    public List<Data> getAllData() {
        return dataRepo.findAll();
    }

    @Override
    public Data getDataById(Long id) {
        Optional<Data> expense = dataRepo.findByUserIdAndId(userService.getLoggedInUser().getId(),id);
        if(expense.isPresent()){
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense is not found for the id "+id);
    }

    @Override
    public void deleteById(Long id) {
        dataRepo.deleteById(id);
    }

    @Override
    public void saveData(Data data) {
        data.setUser(userService.getLoggedInUser());
        data.setIsDone(false);
        dataRepo.saveAndFlush(data);
        data.getDataHolder().getDataStack().add(data);
        dataHolderService.updateIktNum(data.getDataHolder(),data.getDataHolder().getId());
    }

    @Override
    public Data updateDataDetails(Long id, Data data) {
        Data matchingData = getDataById(id);
        matchingData.setAddress(data.getAddress()!=null ? data.getAddress(): matchingData.getAddress());
        matchingData.setDate(data.getDate()!=null ? data.getDate():matchingData.getDate());
        matchingData.setAmount(data.getAmount()!=null ? data.getAmount():matchingData.getAmount());
        matchingData.setDescription(data.getDescription()!=null ? data.getDescription(): matchingData.getDescription());
        matchingData.setCity((data.getCity()!=null ? data.getCity():matchingData.getCity()));
        matchingData.setHszNum(data.getHszNum()!=null ? data.getHszNum(): matchingData.getHszNum());
        matchingData.setFloorNum(data.getFloorNum()!=null ? data.getFloorNum():matchingData.getFloorNum());
        matchingData.setDoorNum(data.getDoorNum()!=null ? data.getDoorNum(): matchingData.getDoorNum());
        matchingData.setIg_category(data.getIg_category()!=null ? data.getIg_category():matchingData.getIg_category());
        matchingData.setVa_category(data.getVa_category()!=null ? data.getVa_category(): matchingData.getVa_category());
        matchingData.setPrincipal(data.getPrincipal()!=null ? data.getPrincipal(): matchingData.getPrincipal());
        matchingData.setPrincipalDelegate(data.getPrincipalDelegate()!=null ? data.getPrincipalDelegate(): matchingData.getPrincipalDelegate());
        return dataRepo.save(matchingData);
    }



    @Override
    public List<Data> getByKeyword(String keyWord) {
        return dataRepo.findByKeyword(keyWord);
    }
//
//    @Override
//    public List<Data> readByName(String name, Pageable page) {
//        return dataRepo.findByUserAndNameContaining(userService.getLoggedInUser().getId(), name,page).toList();
//    }

//    @Override
//    public List<Data> readByDate(Date startDate, Date endDate, Pageable page) {
//        if(startDate==null){
//            startDate = new Date(0);
//        }
//        if(endDate==null){
//            endDate = new Date(System.currentTimeMillis());
//        }
//        Page<Data> pages = expenseRepo.findByUserAndDateBetween(userService.getLoggedInUser().getId(), startDate,endDate,page);
//        return pages.toList();
//    }

}
