package com.eshop.sonny.service.review;

import com.eshop.sonny.dto.Request.ReviewRequestDto;
import com.eshop.sonny.dto.Response.ReviewResponseDto;
import com.eshop.sonny.mapper.mappersClasses.ReviewMapper;

import com.eshop.sonny.model.Review;
import com.eshop.sonny.repository.AdminRepository;
import com.eshop.sonny.repository.ReviewRepository;

import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AdminRepository adminRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, AdminRepository adminRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.adminRepository = adminRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewResponseDto createReview(ReviewRequestDto reviewDTO) {
        Review review = reviewMapper.reviewRequestDtoToEntity(reviewDTO);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.reviewEntityToResponseDto(savedReview);
    }

    @Override
    public void deleteReview(Long reviewId, String username) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        // Check if user is admin or review owner
        if (!isOwnerOrAdmin(review, username)) {
            throw new RuntimeException("You are not authorized to delete this review"); //throw unauthorized exception
        }

        reviewRepository.delete(review);
    }

    @Override
    public ReviewResponseDto updateReview(Long reviewId, ReviewRequestDto reviewDTO, String username) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!existingReview.getBuyer().getUsername().equals(username)) {
            throw new RuntimeException("You can only update your own reviews"); //throw unauthorized exception
        }

        existingReview.setDescription(reviewDTO.getDescription());
        existingReview.setRating(reviewDTO.getRating());
        Review updatedReview = reviewRepository.save(existingReview);
        return reviewMapper.reviewEntityToResponseDto(updatedReview);
    }

    private boolean isOwnerOrAdmin(Review review, String username) {
        return review.getBuyer().getUsername().equals(username) ||
                userIsAdmin(username);
    }

    private boolean userIsAdmin(String username) {
        return adminRepository.existsByUserName(username);
    }
}
