package com.example.AuthDemo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class UploadImage {
    private final static String UPLOAD_DIR = "uploads/";
    private static Path path;
    public static String UploadImages(MultipartFile file){
// Kiểm tra xem file có tồn tại hay không
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
        }

        try {
            // Đảm bảo thư mục lưu trữ tồn tại
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Xây dựng đường dẫn tệp lưu trữ
            path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());

            // Lưu tệp vào thư mục đã chỉ định
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return path.toString();
    }
}
