package com.base.ecommerce.api;

import com.base.ecommerce.core.utils.ResponseHandler;
import com.base.ecommerce.model.Category;
import com.base.ecommerce.service.CategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController extends BaseRestController{

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get-category-data")
    public ResponseEntity<Object> getCategoryData(){
        return ResponseHandler.jsonGenerateResponse("listed" , HttpStatus.OK,this.categoryService.getCategoryData());
    }

    @PostMapping("/categories")
    public ResponseEntity<Object> addCategory(@RequestBody Category category){
        return ResponseHandler.jsonGenerateResponse("listed" , HttpStatus.CREATED,this.categoryService.addCategory(category));
    }


    @DeleteMapping("/delete")
    public void deleteProductData(@RequestParam Long id){
        this.categoryService.deleteProductData(id);
    }



}
