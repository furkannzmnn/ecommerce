package com.base.ecommerce.api;

import com.base.ecommerce.core.utils.ResponseApi;
import com.base.ecommerce.dto.ProductDto;
import com.base.ecommerce.dto.request.ProductRequest;
import com.base.ecommerce.model.ProductStatus;
import com.base.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController extends BaseRestController{

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping( value = "/products/{page}/{count}")
    public ResponseEntity<ResponseApi> getProductData(@PathVariable(required = false) int page, @PathVariable(required = false) int count){
        return ResponseEntity.ok(
                new ResponseApi.ResponseBuilder()
                        .Message("product listed")
                        .StatusCode(201)
                        .HttpStatus(HttpStatus.CREATED)
                        .Data(Map.of("data" , productService.getProductData(page, count)))
                        .builder()
        );
    }

    @PostMapping("/add-product")
    public ResponseEntity<ResponseApi> addProduct(@Valid @RequestBody ProductRequest productRequest, ProductStatus status){
        return ResponseEntity.ok(
                new ResponseApi.ResponseBuilder()
                        .Message("product created")
                        .StatusCode(201)
                        .HttpStatus(HttpStatus.CREATED)
                        .Data(Map.of("data" , productService.addProduct(productRequest,status)))
                        .builder()
        );
    }

    @PostMapping("/searches/{keyword}")
    public ResponseEntity<ProductDto> searchProduct(@PathVariable String keyword){

        return ResponseEntity.ok(this.productService.getSearchProduct(keyword));
    }

    @GetMapping("/get-by-products-price-sorted")
    public ResponseEntity<List<ProductDto>> sortedProductPrice(){
        return ResponseEntity.ok(this.productService.sortedProductPrice());
    }
    @GetMapping("/get-by-name-and-categorys")
    public ResponseEntity<List<ProductDto>> getByNameAndCategory(String productName , Long categoryId){
        return ResponseEntity.ok(this.productService.getByNameAndCategory(productName,categoryId));
    }

    @PostMapping(value = "/images"  )
    public ResponseEntity<?> uploadProductImage(@RequestParam("file") MultipartFile multipartFile, int productId){
        return ResponseEntity.ok(
                this.productService.uploadProductImage(multipartFile, productId)
        );
    }
    @GetMapping("/get-by-id-product/{id}")
    public ResponseEntity<ProductDto> getByIdProduct(@PathVariable int id){
        return ResponseEntity.ok(
                this.productService.getByIdProduct(id)
        );
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductData(@PathVariable int id){
        this.productService.deleteProductData(id);
    }

    @PostMapping("/albums")
    public ResponseEntity<?> uploadAlbums(@RequestParam("file") MultipartFile[] multipartFiles) throws IOException {
        return ResponseEntity.ok(this.productService.uploadAlbums(multipartFiles));
    }

    }
