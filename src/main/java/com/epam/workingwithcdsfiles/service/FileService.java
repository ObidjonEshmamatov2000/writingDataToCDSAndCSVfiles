package com.epam.workingwithcdsfiles.service;

import com.epam.workingwithcdsfiles.model.FileContent;
import com.epam.workingwithcdsfiles.model.FileInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileService {
    public String uploadFile(MultipartFile file) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getOriginalFilename());
        fileInfo.setSize(file.getSize());

        FileContent fileContent = new FileContent();
        fileContent.setId(fileInfo.getId());
        try {
            fileContent.setContent(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        writeFileInfoToCSV(fileInfo);
        writeFileContentToCSV(fileContent);

        return fileInfo.getId().toString();
    }

    private void writeFileInfoToCSV(FileInfo fileInfo) {
        File file = new File("./db/data/my.files-FileInfo.csv");
        try(PrintWriter printWriter = new PrintWriter(file);) {
            String st = "ID;name;size;date";
            printWriter.write(st);
            printWriter.println();
            String sb = fileInfo.getId() +
                    ";" +
                    fileInfo.getName() +
                    ";" +
                    fileInfo.getSize() +
                    ";" +
                    fileInfo.getDate();
            printWriter.write(sb);
            printWriter.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFileContentToCSV(FileContent fileContent) {
        String s = new String(fileContent.getContent());
        File file = new File("./db/data/my.files-FileContent.csv");
        try (PrintWriter printWriter = new PrintWriter(file)) {
            String st = "ID;content";
            printWriter.write(st);
            printWriter.println();
            String sb = fileContent.getId() + ";" + s;
            printWriter.write(sb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
