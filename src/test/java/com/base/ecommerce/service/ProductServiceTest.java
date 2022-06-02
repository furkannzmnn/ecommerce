package com.base.ecommerce.service;

import com.base.ecommerce.core.image.ImplUploadService;
import com.base.ecommerce.dto.converter.ProductDtoConverter;
import com.base.ecommerce.dto.request.ProductRequest;
import com.base.ecommerce.model.Category;
import com.base.ecommerce.model.Product;
import com.base.ecommerce.model.ProductPrice;
import com.base.ecommerce.model.ProductStatus;
import com.base.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


    @InjectMocks
    private ProductService productService;
    @Mock private ProductRepository productRepository;
    @Mock private ProductDtoConverter productDtoConverter;
    @Mock private ImplUploadService implUploadService;
    @Mock private AfterLiveProcess afterLiveProcess;
    @Mock private ProductViewedCountService countService;
    @Mock private KafkaTemplate<String, Product> kafkaTemplate;

    @org.junit.jupiter.api.Test
    void save() {
        Mockito.when(afterLiveProcess.afterLiveProcess(2)).thenReturn(new AfterLiveProcess(null, null));
        productService.addProduct(new ProductRequest.Builder().productDesc("").buyerId(2).productName("")
                        .productTitle("").productPrice(new ProductPrice()).productStatus(ProductStatus.ACTIVE).creatAt(LocalDateTime.MAX)
                .category(new Category())
                        .updateAt(LocalDateTime.now())
                .build(), ProductStatus.ACTIVE);
    }

}