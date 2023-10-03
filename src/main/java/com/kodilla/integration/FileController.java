package com.kodilla.integration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;


@RestController
public class FileController {
    private static final String filepath = "C:\\Users\\piotr\\IdeaProjects\\file-integration\\data\\input\\";

    @PostMapping("/newFile")
    public void createFile(@RequestBody TextObject textObject) {

        try {
            File file = new File(filepath + textObject.getName() + ".txt");
            FileWriter writer = new FileWriter(file);
            writer.write(textObject.getBody());
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/files")
    public ResponseEntity<String> getFilesList(){
        String output = "List of files:";
        File directory = new File("C:\\Users\\piotr\\IdeaProjects\\file-integration\\data\\output");
        File[] contentsOfDirectory = directory.listFiles();
        assert contentsOfDirectory != null;
        for(File object : contentsOfDirectory){
            if(object.isFile()) output += "\n" + object.getName();
        }
        return ResponseEntity.ok(output);
    }
}
