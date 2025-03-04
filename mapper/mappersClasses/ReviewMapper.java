package com.eshop.sonny.mapper.mappersClasses;

import com.eshop.sonny.dto.Request.ReviewRequestDto;
import com.eshop.sonny.dto.Response.ReviewResponseDto;
import com.eshop.sonny.model.Buyer;
import com.eshop.sonny.model.Review;
import com.eshop.sonny.repository.BuyerRepository;
import com.eshop.sonny.repository.ProductRepository;
import org.springframework.stereotype.Component;


@Component
public class ReviewMapper {
    private final BuyerRepository buyerRepository;
    private final ProductRepository productRepository;

    public ReviewMapper(BuyerRepository buyerRepository, ProductRepository productRepository) {
        this.buyerRepository = buyerRepository;
        this.productRepository = productRepository;
    }

    public Review reviewRequestDtoToEntity(ReviewRequestDto dto) {

        Buyer buyer = buyerRepository.findByUserName(dto.getBuyerUsername());
        if(buyer == null)
            throw new RuntimeException();

        return Review.builder()
                .description(dto.getDescription())
                .rating(dto.getRating())
                .buyer(buyer)
                      //  .orElseThrow(() -> new RuntimeException("Buyer not found")))
                .product(productRepository.findById(dto.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found")))
                .build();
    }

    public ReviewResponseDto reviewEntityToResponseDto(Review review) {
        return ReviewResponseDto.builder()
                .id(review.getId())
                .buyerUsername(review.getBuyer().getUsername())
                .productId(review.getProduct().getId())
                .description(review.getDescription())
                .rating(review.getRating())
                .createdAt(review.getCreated_at())
                .updatedAt(review.getUpdated_at())
                .build();
    }
}
