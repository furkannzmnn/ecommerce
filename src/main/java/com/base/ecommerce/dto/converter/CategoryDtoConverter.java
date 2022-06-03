package com.base.ecommerce.dto.converter;

import com.base.ecommerce.dto.CategoryDto;
import com.base.ecommerce.model.Category;
import org.springframework.stereotype.Component;

public final class CategoryDtoConverter {
    public static CategoryDto convertToCategory(Category from){
        return new CategoryDto(
               from.getId(),
               from.getCategoryName()
        );
    }
}
