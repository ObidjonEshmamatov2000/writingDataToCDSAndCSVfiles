package com.epam.workingwithcdsfiles;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
        File file = new File("File_Log_Info.csv");
        try (FileOutputStream os = new FileOutputStream(file)) {
            String sb = fileInfo.getId() +
                    ";" +
                    fileInfo.getName() +
                    ";" +
                    fileInfo.getSize() +
                    ";" +
                    fileInfo.getDate();
            char[] chars = sb.toCharArray();
            for (char c: chars) {
                os.write(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFileContentToCSV(FileContent fileContent) {
        String s = new String(fileContent.getContent());
        File file = new File("File_Content.csv");
        try (FileOutputStream os = new FileOutputStream(file)) {
            String sb = fileContent.getId() +
                    ";" + s;
            char[] chars = sb.toCharArray();
            for (char c: chars) {
                os.write(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
