package com.base.ecommerce.service;

import com.base.ecommerce.dto.ErrorCode;
import com.base.ecommerce.dto.ProductDto;
import com.base.ecommerce.dto.converter.ProductDtoConverter;
import com.base.ecommerce.dto.request.ProductIsActiveUpdateRequest;
import com.base.ecommerce.exception.customException.ProductNotFoundException;
import com.base.ecommerce.model.Product;
import com.base.ecommerce.repository.AdvertisementRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdvertisementService {

    private final ProductService productService;
    private final AdvertisementRepository advertisementRepository;
    private final ProductDtoConverter productDtoConverter;

    public AdvertisementService(ProductService productService,
                                AdvertisementRepository advertisementRepository, ProductDtoConverter productDtoConverter) {
        this.productService = productService;
        this.advertisementRepository = advertisementRepository;
        this.productDtoConverter = productDtoConverter;
    }

    public ProductDto IsTheProductSold(ProductIsActiveUpdateRequest request) {
        Optional<Product> optionalProduct = Optional.of(productService.findByIdProduct(request.getId()));
        optionalProduct.ifPresent(products -> {
            products.setActive(request.isActive());
            advertisementRepository.save(products);
        });
        return optionalProduct.map(productDtoConverter::convertToProduct).orElseThrow(
                ()-> new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND.name())
        );
    }

    public List<ProductDto> isActiveProductList(){

        List<Product> product = advertisementRepository.getByIsActiveTrue();
        return product.stream()
                .map(productDtoConverter::convertToProduct)
                .collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void isActiveProductListStateUpdate(){

        // create last day date
         List<Product> product = advertisementRepository.getByIsActiveTrue();
         product.stream()
                 .filter(times -> times.getUpdateAt().isBefore(times.getCreatAt()))
                 .map(timesTamp -> new Product.Builder().isActive(false).build())
                 .findFirst()
                 .ifPresent(advertisementRepository::save);
    }
    


    public List<ProductDto> isNotActiveProductList(){
        List<Product> product = advertisementRepository.getByIsActiveFalse();
        return product.stream()
                .map(productDtoConverter::convertToProduct)
                .collect(Collectors.toList());
    }
}
