package com.base.ecommerce.service;

import com.base.ecommerce.core.utils.StringUtils;
import com.base.ecommerce.dto.CategoryDto;
import com.base.ecommerce.dto.ErrorCode;
import com.base.ecommerce.dto.converter.CategoryDtoConverter;
import com.base.ecommerce.exception.GenericException;
import com.base.ecommerce.exception.customException.CategoryInvalidException;
import com.base.ecommerce.model.Category;
import com.base.ecommerce.repository.CategoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter categoryDtoConverter;
    public static final String CATEGORY_INVALID = "Oppss.... There was a problem creating the category.";


    public CategoryService(CategoryRepository categoryRepository,CategoryDtoConverter categoryDtoConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoConverter = categoryDtoConverter;
    }

    @Cacheable(value = "category")
    public List<CategoryDto> getCategoryData(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryDtoConverter::convertToCategory)
                .collect(Collectors.toList());
    }

    public CategoryDto addCategory(Category category){

         if(category.getProduct() == null || StringUtils.isEmpty(category.getCategoryName())){
            throw new CategoryInvalidException(CATEGORY_INVALID);
        }
        return categoryDtoConverter.convertToCategory(
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
