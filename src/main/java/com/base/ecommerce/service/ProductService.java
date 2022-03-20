package com.base.ecommerce.service;

import com.base.ecommerce.core.image.ImplUploadService;
import com.base.ecommerce.dto.ErrorCode;
import com.base.ecommerce.dto.ProductDto;
import com.base.ecommerce.dto.converter.ProductDtoConverter;
import com.base.ecommerce.dto.request.ProductRequest;
import com.base.ecommerce.exception.customException.ProductNotFoundException;
import com.base.ecommerce.model.Product;
import com.base.ecommerce.model.ProductStatus;
import com.base.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoConverter productDtoConverter;
    private final ImplUploadService implUploadService;
    private final KafkaTemplate<String, Product> kafkaTemplate;



    public ProductService(ProductRepository productRepository, ProductDtoConverter productDtoConverter,
                          ImplUploadService implUploadService, KafkaTemplate<String, Product> kafkaTemplate) {
        this.productRepository = productRepository;
        this.productDtoConverter = productDtoConverter;
        this.implUploadService = implUploadService;
        this.kafkaTemplate = kafkaTemplate;
    }


    public List<ProductDto> getProductData(int page, int count) {
        return this.productRepository.findAll(PageRequest.of(page, count))
                .stream()
                .map(productDtoConverter::convertToProduct)
                .collect(Collectors.toList());
    }



    @Transactional
    public ProductDto addProduct(ProductRequest productRequest, ProductStatus status) {

        ProductLiveValidation.isValid(productRequest);
        final Product product = new Product.Builder()
                .productTitle(productRequest.getProductTitle())
                .productDesc(productRequest.getProductDesc())
                .productName(productRequest.getProductName())
                .category(productRequest.getCategory())
                .creatAt(productRequest.getCreatAt())
                .updateAt(Objects.requireNonNull(productRequest.getUpdateAt()))
                .productStatus(status)
                .productPrice(productRequest.getProductPrice())
                .build();


        CompletableFuture.runAsync(() -> {
                    ListenableFuture<SendResult<String, Product>> future = kafkaTemplate.send("product", product);
            future.addCallback(
                    result -> System.out.println("Sent message: " + product.getProductName()),
                    ex -> System.out.println("Unable to send message: " + ex.getMessage())
            );
                });

        return productDtoConverter.convertToProduct(productRepository.save(product));

    }

    public List<ProductDto> getByNameAndCategory(String productName, Long categoryId) {
        return this.productRepository.getByProductNameOrCategory_Id(productName, categoryId)
                .stream()
                .map(productDtoConverter::convertToProduct)
                .collect(Collectors.toList());
    }

    protected Product findSearchProduct(String name) {
        return this.productRepository.getProductByProductName(name)
                .orElseThrow(
                        () -> new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND.name())
                );
    }

    public ProductDto getSearchProduct(String keyword) {
        return productDtoConverter.convertToProduct(findSearchProduct(keyword));

    }

    public List<ProductDto> sortedProductPrice() {
        Sort sort = Sort.by(Sort.Direction.ASC, "productPrice");
        return this.productRepository.findAll(sort)
                .stream()
                .map(productDtoConverter::convertToProduct)
                .collect(Collectors.toList());
    }

    protected Product findByIdProduct(int id) {
        return this.productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException(ErrorCode.PRODUCT_NOT_FOUND.name())
                );
    }

    public ProductDto getByIdProduct(int id) {
        return this.productDtoConverter.convertToProduct(
                findByIdProduct(id)
        );
    }

    public void deleteProductData(int id) {
        Product product = findByIdProduct(id);
        this.productRepository.deleteById(product.getId());
    }

    @Async
    @Transactional
    public void saveAll(List<Product> products){
        productRepository.saveAll(products);
    }

    @Transactional
    public String uploadProductImage(MultipartFile multipartFile, int productId) {

        Product product = productRepository.getById(productId);
        String url = implUploadService.upload(multipartFile);
        product.setProductImageURL(url);
        productRepository.save(product);
        return "saved:" + url;
    }

    @Transactional
    public List<String> uploadAlbums(MultipartFile[] multipartFiles) {
        return implUploadService.uploadMultipleImage(multipartFiles);
    }





}