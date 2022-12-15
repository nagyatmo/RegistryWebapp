package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface DataService {
    List<Data> getAllData();
    Data getDataById(Long id);
    void deleteById(Long id);
    Data saveData(Data data);
    Data updateDataDetails(Long id, Data expense);
    List<Data> getByKeyword(String hszNum);
}
