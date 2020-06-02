package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.service.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.InputStream;
import java.util.ResourceBundle;

/**
 * @author wisdom
 */
@RequestMapping("/")
@RestController
public class TestController {

    @Autowired
    FileSystemService fileSystemService;

    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping("/test")
    public void test(){

        try {
//            Resource resource = resourceLoader.getResource("classpath:a.txt");
//            InputStream inputStream = resource.getInputStream();
            fileSystemService.uploadFile("/upload/c.txt",new File("D:\\sftp\\time.txt"));
//            fileSystemService.uploadFile("/upload/b.txt",inputStream);
//            fileSystemService.uploadFile("/a.txt",new File("a.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
