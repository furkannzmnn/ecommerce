package com.base.ecommerce.dto.converter;

import com.base.ecommerce.dto.FavoriteDto;
import com.base.ecommerce.model.Favorite;
import org.springframework.stereotype.Component;

@Component
public class FavoriteDtoConverter {

    public FavoriteDto convertToFavorite(Favorite from){
        return new FavoriteDto(
                from.getProductId()
        );
    }
}
