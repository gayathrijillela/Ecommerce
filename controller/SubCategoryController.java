package com.eshop.sonny.controller;

import com.eshop.sonny.dto.Request.SubCategoryRequestDto;
import com.eshop.sonny.dto.Response.ApiResponseDto;
import com.eshop.sonny.dto.Response.SubCategoryResponseDto;
import com.eshop.sonny.service.SubCategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories/{categoryName}/subcategories")
@Tag(name = "Subcategory Management", description = "Operations related to subcategories")
public class SubCategoryController {

    private final SubCategoriesService subCategoriesService;

    public SubCategoryController(SubCategoriesService subCategoriesService) {
        this.subCategoriesService = subCategoriesService;
    }

    @Operation(summary = "Add a subcategory", description = "Creates a new subcategory under a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategory added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid subcategory details"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<SubCategoryResponseDto>> addSubCategory(
            @Parameter(description = "Category name", required = true) @PathVariable String categoryName,
            @RequestBody SubCategoryRequestDto subCategory) {
        boolean isAdded = subCategoriesService.addSubCategory(categoryName, subCategory);
        if (isAdded) {
            SubCategoryResponseDto newSubCategory = subCategoriesService.getSubCategory(categoryName, subCategory.getName());
            return ResponseEntity.ok(new ApiResponseDto<>(true, HttpStatus.OK.value(), newSubCategory, "Subcategory added successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(false, HttpStatus.BAD_REQUEST.value(), null, "Failed to add subcategory"));
    }

    @Operation(summary = "Delete a subcategory", description = "Removes a subcategory by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategory deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid subcategory details"),
            @ApiResponse(responseCode = "404", description = "Subcategory not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{subCategoryName}")
    public ResponseEntity<ApiResponseDto<String>> deleteSubCategory(
            @Parameter(description = "Category name", required = true) @PathVariable String categoryName,
            @Parameter(description = "Subcategory name", required = true) @PathVariable String subCategoryName) {
        boolean isDeleted = subCategoriesService.deleteSubCategory(categoryName, subCategoryName);
        if (isDeleted) {
            return ResponseEntity.ok(new ApiResponseDto<>(true, HttpStatus.OK.value(), "Subcategory deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDto<>(false, HttpStatus.NOT_FOUND.value(), "Failed to delete subcategory"));
    }

    @Operation(summary = "Update a subcategory", description = "Updates the details of an existing subcategory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategory updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid subcategory details"),
            @ApiResponse(responseCode = "404", description = "Subcategory not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public ResponseEntity<ApiResponseDto<SubCategoryResponseDto>> updateSubCategory(
            @Parameter(description = "Category name", required = true) @PathVariable String categoryName,
            @RequestBody SubCategoryRequestDto subCategory) {
        boolean isUpdated = subCategoriesService.updateSubCategory(categoryName, subCategory);
        if (isUpdated) {
            SubCategoryResponseDto updatedSubCategory = subCategoriesService.getSubCategory(categoryName, subCategory.getName());
            return ResponseEntity.ok(new ApiResponseDto<>(true, HttpStatus.OK.value(), updatedSubCategory, "Subcategory updated successfully"));
        }
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(false, HttpStatus.BAD_REQUEST.value(), null, "Failed to update subcategory"));
    }

    @Operation(summary = "Get a specific subcategory", description = "Retrieves details of a single subcategory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subcategory retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Subcategory not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{subCategoryName}")
    public ResponseEntity<ApiResponseDto<SubCategoryResponseDto>> getSubCategory(
            @Parameter(description = "Category name", required = true) @PathVariable String categoryName,
            @Parameter(description = "Subcategory name", required = true) @PathVariable String subCategoryName) {
        SubCategoryResponseDto subCategory = subCategoriesService.getSubCategory(categoryName, subCategoryName);
        if (subCategory != null) {
            return ResponseEntity.ok(new ApiResponseDto<>(true, HttpStatus.OK.value(), subCategory, "Subcategory retrieved successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDto<>(false, HttpStatus.NOT_FOUND.value(), null, "Subcategory not found"));
    }
}
