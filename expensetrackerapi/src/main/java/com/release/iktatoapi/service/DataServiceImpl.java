package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.data.entity.UploadedFile;
import com.release.iktatoapi.data.repository.DataRepository;
import com.release.iktatoapi.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private UserService userService;

    @Autowired
    private DataRepository dataRepo;

    @Autowired
    private DataHolderService dataHolderService;

    @Autowired
    private UploadedFileService uploadedFileService;

    @Override
    public List<Data> getAllData() {
        return dataRepo.findAll();
    }

    @Override
    public Data getDataById(Long id) {
        Optional<Data> foundData = dataRepo.findById(id);
        if(foundData.isPresent()){
            return foundData.get();
        }
        throw new ResourceNotFoundException("Az iktatás nem található ezzel az azonosítóval: "+id);
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
        dataHolderService.updateIktNum(data.getDataHolder());
        data.setDataIktNum(data.getDataHolder().getIktNum());
        dataRepo.saveAndFlush(data);
    }

    @Override
    public Data updateDataDetails(Long id, Data data) {
        Data matchingData = getDataById(id);
        matchingData.setDataHolder(data.getDataHolder()!=null ? data.getDataHolder():matchingData.getDataHolder());
        matchingData.setIsDone(data.getIsDone()!=null ? data.getIsDone():matchingData.getIsDone());
        matchingData.setIrNum(data.getIrNum()!=null? data.getIrNum():matchingData.getIrNum());
        matchingData.setAddress(data.getAddress()!=null ? data.getAddress(): matchingData.getAddress());
        matchingData.setStreet(data.getStreet()!=null ? data.getStreet(): matchingData.getStreet());
        matchingData.setStreetNum(data.getStreetNum()!=null ? data.getStreetNum(): matchingData.getStreetNum());
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
        matchingData.setPerNum(data.getPerNum()!=null ? data.getPerNum() : matchingData.getPerNum());
        matchingData.setUrgent(data.isUrgent());
        matchingData.setDataIktNum(matchingData.getDataHolder().getIktNum());
        return dataRepo.save(matchingData);
    }

    @Override
    public Page<Data> getByKeyword(String keyword, Pageable pageable) {
        return dataRepo.findByKeyword(keyword, pageable);
    }

    @Override
    public Data store(MultipartFile file, Data data) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        UploadedFile newUploadFile = new UploadedFile();
        newUploadFile.setData(file.getBytes());
        newUploadFile.setType(file.getContentType());
        newUploadFile.setName(fileName);
        uploadedFileService.saveUploadedFile(newUploadFile);
        data.setUploadedFile(newUploadFile);
        return dataRepo.save(data);
    }

    public Data setToUrgentIfUrgent(Long id){
        Optional<Data> dataById = Optional.ofNullable(getDataById(id));
        if(ChronoUnit.DAYS.between(LocalDate.now(),dataById.get().getDate().toLocalDate())<=7 && !dataById.get().getIsDone()){
            dataById.get().setUrgent(true);
        }else{
            dataById.get().setUrgent(false);
        }
        updateDataDetails(id,dataById.get());
        return dataById.get();
    }

}
