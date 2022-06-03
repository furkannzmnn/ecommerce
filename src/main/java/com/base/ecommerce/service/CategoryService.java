package com.base.ecommerce.service;

import com.base.ecommerce.core.utils.StringUtils;
import com.base.ecommerce.dto.CategoryDto;
import com.base.ecommerce.dto.ErrorCode;
import com.base.ecommerce.dto.converter.CategoryDtoConverter;
import com.base.ecommerce.exception.GenericException;
import com.base.ecommerce.exception.customException.CategoryInvalidException;
import com.base.ecommerce.model.Category;
import com.base.ecommerce.repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger LOGGER = LogManager.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    public static final String CATEGORY_INVALID = "Oppss.... There was a problem creating the category.";


    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Cacheable(value = "category")
    public List<CategoryDto> getCategoryData(){
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDtoConverter::convertToCategory)
                .collect(Collectors.toList());
    }

    public CategoryDto addCategory(Category category){

         if(category.getProduct() == null || StringUtils.isEmpty(category.getCategoryName())){
            throw new CategoryInvalidException(CATEGORY_INVALID);
        }

        LOGGER.info("Category created successfully");
        LOGGER.info("Category name: {}", category.getCategoryName());
        LOGGER.info("Category id: {}", category.getId());
        return CategoryDtoConverter.convertToCategory(
                categoryRepository.save(category)
        );

    }

    public void deleteProductData(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> GenericException.builder()
                        .errorCode(ErrorCode.CATEGORY_NOT_FOUND)
                        .status(HttpStatus.NOT_FOUND)
                        .data(Map.of("error","category not found"))
                        .build());

        this.categoryRepository.deleteById(Objects.requireNonNull(category.getId()));
    }

}
