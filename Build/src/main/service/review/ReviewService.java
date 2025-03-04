package com.eshop.sonny.service.review;

import com.eshop.sonny.dto.Request.ReviewRequestDto;
import com.eshop.sonny.dto.Response.ReviewResponseDto;


public interface ReviewService {
    ReviewResponseDto createReview(ReviewRequestDto reviewDTO);
    void deleteReview(Long reviewId, String username);
    ReviewResponseDto updateReview(Long reviewId, ReviewRequestDto reviewDTO, String username);
}
