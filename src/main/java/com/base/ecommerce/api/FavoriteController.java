package com.base.ecommerce.api;

import com.base.ecommerce.core.utils.ResponseHandler;
import com.base.ecommerce.dto.request.FavoriteSaveRequest;
import com.base.ecommerce.service.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController extends BaseRestController{

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/product/favorite")
    public ResponseEntity<Object> addFavoriteProduct(@RequestBody FavoriteSaveRequest request){
        return ResponseHandler.jsonGenerateResponse(
                "ok", HttpStatus.CREATED,this.favoriteService.addFavoriteProduct(request)
        );
    }

    @GetMapping("/favorite-all")
    public ResponseEntity<?> getFavorites() {
        return ResponseEntity.ok(this.favoriteService.getFavorite());
    }
}
