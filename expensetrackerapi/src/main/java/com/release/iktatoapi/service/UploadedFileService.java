package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.Data;
import com.release.iktatoapi.data.entity.UploadedFile;

public interface UploadedFileService {
    UploadedFile getUploadedFileById(Long id);
    void deleteById(Long id);
    void saveUploadedFile(UploadedFile file);
}
