package com.base.ecommerce.api;

import com.base.ecommerce.core.utils.ResponseApi;
import com.base.ecommerce.dto.ProductDto;
import com.base.ecommerce.dto.request.ProductIsActiveUpdateRequest;
import com.base.ecommerce.service.AdvertisementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("api/v1/product/advertisements")
public class AdvertisementController extends BaseRestController{

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PutMapping("/")
    public ResponseEntity<ResponseApi> setAdvertisementIsActive(@RequestBody ProductIsActiveUpdateRequest request){
        return ResponseEntity.ok(
                new ResponseApi.ResponseBuilder()
                        .timestamps(now())
                        .Message("STATUS CONVERTED")
                        .HttpStatus(HttpStatus.OK)
                        .StatusCode(200)
                        .DeveloperMessage("ADVERTISEMENT IS ACTIVE = FALSE")
                        .Data(Map.of("data", advertisementService.IsTheProductSold(request)))
                        .builder()
        );
    }
    @GetMapping("/active/list")
    public ResponseEntity<List<ProductDto>> isActiveProductList(){
        return ResponseEntity.ok(this.advertisementService.isActiveProductList());
    }

    @GetMapping("/not-active/list")
    public ResponseEntity<List<ProductDto>> isNotActiveProductList(){
        return ResponseEntity.ok(this.advertisementService.isNotActiveProductList());
    }

}
