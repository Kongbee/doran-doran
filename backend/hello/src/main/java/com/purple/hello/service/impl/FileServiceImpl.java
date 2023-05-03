package com.purple.hello.service.impl;

import com.purple.hello.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public boolean compareExtensions(String fileExtension, String[] imageExtensions) {
        for(String imageExtension: imageExtensions){
            if(fileExtension.equals(imageExtension)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getExtension(String fileName) {
        String[] fileNameTokens = fileName.split("\\.");
        int fileNameTokenLastIndex = fileNameTokens.length-1;
        String fileNameExtension = fileNameTokens[fileNameTokenLastIndex];
        return fileNameExtension;
    }
}
