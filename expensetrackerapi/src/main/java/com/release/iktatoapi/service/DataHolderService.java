package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.DataHolder;

import java.util.List;

public interface DataHolderService {
    DataHolder getDataHolderById(Long id);
    DataHolder saveDataHolder(DataHolder dataHolder);
    List<DataHolder> getAllDataHolder();
    DataHolder updateIktNum(DataHolder dataHolder, Long id);
}
