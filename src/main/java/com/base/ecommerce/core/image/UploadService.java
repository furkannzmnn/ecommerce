package com.base.ecommerce.core.image;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String upload(MultipartFile multipartFile);
}
