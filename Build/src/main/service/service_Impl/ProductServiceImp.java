package com.eshop.sonny.service.service_Impl;

import com.eshop.sonny.QuerySpecification.QueryProductSpecification;
import com.eshop.sonny.QuerySpecification.Spacifications.ProductSpecification;
import com.eshop.sonny.dto.Request.ProductCreationRequestDto;
import com.eshop.sonny.dto.Response.ProductsDetailsDto;
import com.eshop.sonny.mapper.mappersClasses.ProductMapper;
import com.eshop.sonny.model.Category;
import com.eshop.sonny.model.MySeller;
import com.eshop.sonny.model.Product;
import com.eshop.sonny.model.SubCategory;
import com.eshop.sonny.repository.CategoryRepository;
import com.eshop.sonny.repository.ProductRepository;
import com.eshop.sonny.repository.SellerRepository;
import com.eshop.sonny.repository.SubCategoryRepository;
import com.eshop.sonny.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final SellerRepository sellerRepository;
    private final QueryProductSpecification queryProductSpecification;

    @Override
    public boolean saveProduct(String sellerBusinessName, String categoryName, String subCategoryName, ProductCreationRequestDto productDto) {

        MySeller seller = sellerRepository.findByBusinessName(sellerBusinessName);
        if(seller == null)
            throw new RuntimeException("Seller not found");
        Category category = categoryRepository.findByCategoryName(categoryName);
        if(category == null)
            throw new RuntimeException("Category not found");
        SubCategory subCategory = subCategoryRepository.findBySubcategoryName(subCategoryName);
        if(subCategory == null)
            throw new RuntimeException("SubCategory not found");

        Product newProduct = ProductMapper.ProductCreationDtoToProduct(productDto);
        subCategory.getProducts().add(newProduct);
        // saved directly in the product repository
        seller.getProductsListed().add(newProduct);

        return true;
    }

    @Override
    public boolean deleteProduct(String sellerBusinessName, Long productId) {

        MySeller seller = sellerRepository.findByBusinessName(sellerBusinessName);
        if(seller == null)
            throw new RuntimeException("Seller not found");

        //boolean removed =seller.getProductsListed().removeIf(product -> Objects.equals(product.getId(), productId));

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);

        return true;
    }

    @Override
    public boolean updateProduct(String sellerBusinessName, Long productId, ProductCreationRequestDto product) {
        MySeller seller = sellerRepository.findByBusinessName(sellerBusinessName);
        if(seller == null)
            throw new RuntimeException("Seller not found");
        Product productToUpdate = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        productToUpdate.setProductName(product.getProductName());
        productToUpdate.setProductPrice(product.getProductPrice());
        productToUpdate.setInventoryCount(product.getInventoryCount());
        productToUpdate.setProductDescription(product.getProductDescription());
        productToUpdate.setProductImages(product.getProductImages());

        productRepository.save(productToUpdate);

        return false;
    }

    @Override
    public ProductsDetailsDto getProductDetails(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.ProductToProductDetailsDto(product);

    }

    @Override
    public List<ProductsDetailsDto> searchProducts(ProductSpecification productSpecification) {

        Specification<Product> specification = QueryProductSpecification.buildSpecification(productSpecification);
        List<Product> products = productRepository.findAll(specification);
        if(!products.isEmpty()) {

            return products.stream().map(ProductMapper::ProductToProductDetailsDto).toList();
        }
        return null;
    }


}
