package com.eshop.sonny.controller;

import com.eshop.sonny.dto.Response.ApiResponseDto;
import com.eshop.sonny.dto.Request.CategoryRequestDto;
import com.eshop.sonny.dto.Response.AllCategoryResponse;
import com.eshop.sonny.dto.Response.CategoryResponseDto;
import com.eshop.sonny.dto.Response.ProductsResponseDto;
import com.eshop.sonny.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category Management", description = "Operations related to product categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Save a new category", description = "Creates a new category")
    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto<String>> saveCategory(@RequestBody CategoryRequestDto category) {
        boolean isSaved = categoryService.save(category);
        return ResponseEntity.ok(new ApiResponseDto<>(isSaved, isSaved ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value(), isSaved ? "Category saved successfully" : "Failed to save category"));
    }

    @Operation(summary = "Update an existing category", description = "Modifies an existing category")
    @PutMapping("/update")
    public ResponseEntity<ApiResponseDto<String>> updateCategory(@RequestBody CategoryRequestDto category) {
        boolean isUpdated = categoryService.update(category);
        return ResponseEntity.ok(new ApiResponseDto<>(isUpdated, isUpdated ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value(), isUpdated ? "Category updated successfully" : "Failed to update category"));
    }

    @Operation(summary = "Delete a category", description = "Removes a category by name")
    @DeleteMapping("/delete/{categoryName}")
    public ResponseEntity<ApiResponseDto<String>> deleteCategory(@PathVariable String categoryName) {
        boolean isDeleted = categoryService.delete(categoryName);
        return ResponseEntity.ok(new ApiResponseDto<>(isDeleted, isDeleted ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value(), isDeleted ? "Category deleted successfully" : "Failed to delete category"));
    }

    @Operation(summary = "Get a specific category", description = "Retrieves details of a single category")
    @GetMapping("/{categoryName}")
    public ResponseEntity<ApiResponseDto<CategoryResponseDto>> getSpecificCategory(@PathVariable String categoryName) {
        CategoryResponseDto category = categoryService.getSpacificCategory(categoryName);
        return ResponseEntity.ok(new ApiResponseDto<>(true, HttpStatus.OK.value(), category, "Category retrieved successfully"));
    }

    @Operation(summary = "Get all categories", description = "Retrieves all categories available")
    @GetMapping("/all")
    public ResponseEntity<ApiResponseDto<AllCategoryResponse>> getAllCategories() {
        AllCategoryResponse categories = categoryService.getAllCategories();
        return ResponseEntity.ok(new ApiResponseDto<>(true, HttpStatus.OK.value(), categories, "Categories retrieved successfully"));
    }

    @Operation(summary = "Recommend a product in a category", description = "Adds a product to the recommendation list of a category")
    @PostMapping("/{categoryName}/recommend/{productId}")
    public ResponseEntity<ApiResponseDto<String>> addProductToRecommendation(@PathVariable String categoryName, @PathVariable long productId) {
        boolean isAdded = categoryService.addProductToRecommendation(categoryName, productId);
        return ResponseEntity.ok(new ApiResponseDto<>(isAdded, isAdded ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value(), isAdded ? "Product added to recommendation successfully" : "Failed to add product to recommendation"));
    }

    @Operation(summary = "Remove a recommended product", description = "Removes a product from the recommendation list of a category")
    @DeleteMapping("/{categoryName}/recommend/{productId}")
    public ResponseEntity<ApiResponseDto<String>> deleteProductFromRecommendation(@PathVariable String categoryName, @PathVariable Long productId) {
        boolean isDeleted = categoryService.deleteProductFromRecommendation(categoryName, productId);
        return ResponseEntity.ok(new ApiResponseDto<>(isDeleted, isDeleted ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value(), isDeleted ? "Product removed from recommendation successfully" : "Failed to remove product from recommendation"));
    }

    @Operation(summary = "Get products in a special category", description = "Retrieves products from a specific category by ID")
    @GetMapping("/{categoryName}/products/{id}")
    public ResponseEntity<ApiResponseDto<List<ProductsResponseDto>>> getProductsFromSpecialCategory(@PathVariable String categoryName, @PathVariable Long id) {
        List<ProductsResponseDto> products = categoryService.getProductFromSpecialCategory(categoryName, id);
        return ResponseEntity.ok(new ApiResponseDto<>(true, HttpStatus.OK.value(), products, "Products retrieved successfully"));
    }
}
