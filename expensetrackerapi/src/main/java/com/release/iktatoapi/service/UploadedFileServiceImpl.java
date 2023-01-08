package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.UploadedFile;
import com.release.iktatoapi.data.repository.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadedFileServiceImpl implements UploadedFileService{

    @Autowired
    private UploadedFileRepository repository;

    @Override
    public UploadedFile getUploadedFileById(Long id) {
        return repository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void saveUploadedFile(UploadedFile file) {
        repository.saveAndFlush(file);
    }
}
