package com.example.library.code.services.iservices;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IClouddinaryService {
    public Map upload(MultipartFile multipartFile);
}
