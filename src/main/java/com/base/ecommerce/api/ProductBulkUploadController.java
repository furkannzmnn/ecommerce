package com.base.ecommerce.api;

import com.base.ecommerce.service.ProductBulkUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest/admin/bulk")
public class ProductBulkUploadController extends BaseRestController{

    private final ProductBulkUploadService productBulkUploadService;

    public ProductBulkUploadController(ProductBulkUploadService productBulkUploadService) {
        this.productBulkUploadService = productBulkUploadService;
    }

    @PostMapping(value = "/upload",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HttpStatus> uploadProductBulk(@RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(productBulkUploadService.upload(multipartFile));
    }
}
