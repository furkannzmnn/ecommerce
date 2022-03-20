package com.base.ecommerce.service.search;

import com.base.ecommerce.dto.ProductDto;
import com.base.ecommerce.dto.ProductSearchDto;
import com.base.ecommerce.dto.converter.ProductDtoConverter;
import com.base.ecommerce.repository.ProductRepository;
import com.base.ecommerce.repository.specification.ProductSpecification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final ProductRepository productRepository;
    private final ProductDtoConverter converter;

    public SearchService(ProductRepository productRepository, ProductDtoConverter converter) {
        this.productRepository = productRepository;
        this.converter = converter;
    }

    public List<ProductDto> fullSearchProduct(ProductSearchDto searchDto){

        return productRepository.findAll(ProductSpecification.searchAllBy(searchDto))
                .stream()
                .map(converter::convertToProduct)
                .collect(Collectors.toList());
    }
}
