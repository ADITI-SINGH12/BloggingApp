package com.platform.blogging.service.impl;

import com.platform.blogging.service.FileServices;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.io.*;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileServices {
    @Override
    public String uploadImages(String path, MultipartFile image) throws IOException {

        //File name
        String name = image.getOriginalFilename();

        //random file name generator
        String randomID = UUID.randomUUID().toString();
        //abc.png
        String imageName1 = randomID.concat(name.substring(name.lastIndexOf(".")));


        //Fullpath
        String filePath = path + File.separator + imageName1;

        //create folder if not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        //File copy
        Files.copy(image.getInputStream(), Paths.get(filePath));



        return imageName1;
    }

    @Override
    public InputStream getResources(String path, String imageName) throws FileNotFoundException {
        String fullPath = path + File.separator+imageName;

        InputStream inputStream = new FileInputStream(fullPath);

        return inputStream;
    }

    @Override
    public void deleteResource(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}
