package com.eshop.sonny.service.service_Impl;

import com.eshop.sonny.dto.Request.CategoryRequestDto;
import com.eshop.sonny.dto.Request.SubCategoryRequestDto;
import com.eshop.sonny.dto.Response.AllCategoryResponse;
import com.eshop.sonny.dto.Response.CategoryResponseDto;
import com.eshop.sonny.dto.Response.ProductsResponseDto;
import com.eshop.sonny.mapper.mappersClasses.CategoryMapper;
import com.eshop.sonny.mapper.mappersClasses.ProductMapper;
import com.eshop.sonny.mapper.mappersClasses.SubCategoryMapper;
import com.eshop.sonny.model.Category;
import com.eshop.sonny.model.Product;
import com.eshop.sonny.model.SubCategory;
import com.eshop.sonny.repository.CategoryRepository;
import com.eshop.sonny.repository.ProductRepository;
import com.eshop.sonny.repository.SubCategoryRepository;
import com.eshop.sonny.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final SubCategoryMapper subCategoryMapper;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public boolean save(CategoryRequestDto categoryRequestDto) {
        if(categoryRepository.existsByCategoryName(categoryRequestDto.getCategoryName())){
            throw new RuntimeException("Category already exists");
        }
        Category category = CategoryMapper.CategoryResponseDtoTocategory(categoryRequestDto);
        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean update(CategoryRequestDto category) {
        Category categoryExists = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryExists == null){
            throw new RuntimeException("Category not found");
        }

        // should we put all of the fields? discuss
        categoryExists.setCategoryName(category.getCategoryName());
        categoryExists.setCategory_url(category.getCategoryUrl());
        categoryExists.setCategory_banner(category.getCategoryBanner());
        categoryRepository.save(categoryExists);
        return true;
    }

    @Override
    public boolean delete(String categoryName) {
        Category categoryExists = categoryRepository.findByCategoryName(categoryName);

        if(categoryExists == null){
            throw new RuntimeException("Category not found");
        }

        categoryRepository.delete(categoryExists);
        return true;
    }

    @Override
    public CategoryResponseDto getSpacificCategory(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if(category == null){
            throw new RuntimeException("Category not found");
        }

        return CategoryMapper.categoryToCategoryResponseDto(category);
    }

    @Override
    public AllCategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtos = categories.stream()
                .map(CategoryMapper::categoryToCategoryResponseDto)
                .toList();
        AllCategoryResponse allCategoryResponse = new AllCategoryResponse();
        allCategoryResponse.setCategoryResponseDto(categoryResponseDtos);
        return allCategoryResponse;
    }

    @Override
    public boolean addProductToRecommendation(String categoryName, long id) {
        if(!categoryRepository.existsByCategoryName(categoryName))
            throw new RuntimeException("category name dose not exist");
        Category category=categoryRepository.findByCategoryName(categoryName);
        Product product=productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("product not found with this id : "+id)
        );
        List<Product> productList=category.getRecommendations();
        if(productList.contains(product))
            throw new RuntimeException("product exist in the list already");
        productList.add(product);
        return true;
    }

    @Override
    public boolean deleteProductFromRecommendation(String categoryName, Long id) {
        if(!categoryRepository.existsByCategoryName(categoryName))
            throw new RuntimeException("category name dose not exist");
        Category category=categoryRepository.findByCategoryName(categoryName);
        Product product=productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("product not found with this id : "+id)
        );
        List<Product> productList=category.getRecommendations();
        if(!productList.contains(product))
            throw new RuntimeException("product dose not exist in the list");
        productList.remove(product);
        return true;
    }

    @Override
    public List<ProductsResponseDto> getProductFromSpecialCategory(String categoryName, Long id) {
        if(!categoryRepository.existsByCategoryName(categoryName))
            throw new RuntimeException("category name dose not exist");
        Category category=categoryRepository.findByCategoryName(categoryName);
        List<Product> productList=category.getRecommendations();
        if(productList.isEmpty())
            return null;
        return productList.stream()
                .map(ProductMapper::productToProductResponseDto)
                .toList();
    }


}
