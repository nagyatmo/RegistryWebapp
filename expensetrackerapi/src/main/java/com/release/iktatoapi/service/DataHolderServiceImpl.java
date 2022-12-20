package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;
import com.release.iktatoapi.data.repository.DataHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataHolderServiceImpl implements DataHolderService{

    @Autowired
    private DataHolderRepository holderRepository;

    @Autowired
    private DataServiceImpl dataService;



    @Override
    public DataHolder getDataHolderById(Long id) {
        return holderRepository.findById(id).get();
    }


    @Override
    public DataHolder saveDataHolder(DataHolder dataHolder) {

        holderRepository.saveAndFlush(dataHolder);
        dataHolder.setIktNum("BM_"+dataHolder.getId());
        return holderRepository.saveAndFlush(dataHolder);
    }

    @Override
    public List<DataHolder> getAllDataHolder() {
        return holderRepository.findAll();
    }

    @Override
    public DataHolder updateIktNum(DataHolder dataHolder, Long id) {
        int year = Year.now().getValue();
        //newDataHolder.setDataStack(dataHolder.getDataStack()!=null ? dataHolder.getDataStack() : newDataHolder.getDataStack());
        dataHolder.setIktNum("BM_"+dataHolder.getId()+"_"+ dataService.getAllData().stream().filter(n->n.getDataHolder().getId().equals(id)).count() +"_"+year);
        return holderRepository.save(dataHolder);
    }
}
