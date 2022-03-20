package com.base.ecommerce.api;

import com.base.ecommerce.dto.SalesApplicationDto;
import com.base.ecommerce.service.SalesApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesApplicationController extends BaseRestController{

    private final SalesApplicationService salesApplicationService;

    public SalesApplicationController(SalesApplicationService salesApplicationService) {
        this.salesApplicationService = salesApplicationService;
    }

    public ResponseEntity<SalesApplicationDto> apply(SalesApplicationDto dto) {
        return ResponseEntity.ok(this.salesApplicationService.apply(dto));
    }
}
