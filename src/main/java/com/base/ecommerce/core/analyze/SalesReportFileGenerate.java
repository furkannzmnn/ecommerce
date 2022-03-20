package com.base.ecommerce.core.analyze;

import com.base.ecommerce.service.BulkService;
import org.springframework.web.multipart.MultipartFile;

public class SalesReportFileGenerate implements BulkService {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T upload(MultipartFile multipartFiles) {
        return (T) "null";
    }
}
