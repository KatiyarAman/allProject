package com.quokaa.excel.service.impl;

import com.quokaa.excel.config.ApplicationConfig;
import com.quokaa.excel.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private static final Logger logger= LoggerFactory.getLogger(FileUploadServiceImpl.class);

    private static final String FileType="xlsx";

    @Autowired
    ApplicationConfig config;

    private Path fileStorageLocation;

    @PostConstruct
    public void setPath(){
        this.fileStorageLocation= Paths.get(config.getRootDir()).toAbsolutePath().normalize();
    }

    @Override
    public Pair<Boolean, String> storeFile(MultipartFile file) {
         String fileName=file.getOriginalFilename();
         if(file.isEmpty())
             return Pair.of(false, "empty file");

         if(fileName.endsWith("."+FileType)) {
             //fileName = fileName.substring(0, fileName.indexOf("."));
             try (InputStream inputStream = file.getInputStream()) {
                 Files.copy(inputStream, fileStorageLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                 logger.info("file uploaded sucessfully fileName " +fileName);
                 return Pair.of(true, fileName);
             } catch (IOException e) {
                 e.printStackTrace();
                 logger.info("error occured while reading and write the file");
                 return Pair.of(false, "error occured while reading and writing the file");
             }
         }
         else
             return Pair.of(false,"Invalid file type" +fileName);

    }

    @Override
    public File writeInFile(String dir, String fileName) {
        String uploadfile=this.fileStorageLocation+File.separator+dir;
        File file= new File(uploadfile);
        if(!file.exists()){
            file.mkdir();
            file.setReadable(true,false);
            file.setExecutable(true,false);
            file.setWritable(true,false);
        }
        file=new File( uploadfile+File.separator+fileName);
        return  file;
    }
}
