package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public interface DataService {
    List<Data> getAllData();
    Data getDataById(Long id);
    void deleteById(Long id);
    void saveData(Data data);
    Data updateDataDetails(Long id, Data data);
    Page<Data> getByKeyword(String keyword,Pageable pageable);
    Data store(MultipartFile file, Data data) throws IOException;
    Data setToUrgentIfUrgent(Long id);
}
