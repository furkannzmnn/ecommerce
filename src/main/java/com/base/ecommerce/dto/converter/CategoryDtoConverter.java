package com.base.ecommerce.dto.converter;

import com.base.ecommerce.dto.CategoryDto;
import com.base.ecommerce.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoConverter {
    public CategoryDto convertToCategory(Category from){
        return new CategoryDto(
               from.getId(),
               from.getCategoryName()
        );
    }
}
