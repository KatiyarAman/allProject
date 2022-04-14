package com.quokaa.excel.service;

import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileUploadService {

    Pair<Boolean,String> storeFile(MultipartFile file);

    File writeInFile(String dir, String fileName);
}
