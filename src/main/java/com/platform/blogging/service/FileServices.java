package com.platform.blogging.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileServices {
    String uploadImages(String path, MultipartFile image) throws IOException;
    InputStream getResources(String path,String fileName) throws FileNotFoundException;
    void deleteResource(String path);
}
