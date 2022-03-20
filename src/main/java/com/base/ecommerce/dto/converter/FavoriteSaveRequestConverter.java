package com.base.ecommerce.dto.converter;

import com.base.ecommerce.dto.request.FavoriteSaveRequest;
import com.base.ecommerce.model.Favorite;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FavoriteSaveRequestConverter {

    public Favorite favoriteSaveRequestToFavorite(FavoriteSaveRequest favoriteSaveRequest){
        return new Favorite(
                Objects.requireNonNull(favoriteSaveRequest.getProductId())
        );
    }
}
