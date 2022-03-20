package com.base.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

public interface BulkService {

    <T> T upload (final MultipartFile multipartFiles);
}
