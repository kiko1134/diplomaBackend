package com.example.diplomaupdated.service;

import com.example.diplomaupdated.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews();

    void createReviewUser(Long user_id, String content);

    void createReviewWorkshop(Long workshop_id, String content);

    void deleteReview(Long id);
}
