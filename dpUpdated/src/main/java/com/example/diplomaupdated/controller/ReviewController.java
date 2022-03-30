package com.example.diplomaupdated.controller;

import com.example.diplomaupdated.DTO.ReviewDto;
import com.example.diplomaupdated.model.Review;
import com.example.diplomaupdated.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/v1/review")

public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public List<Review> getReviews() {return reviewService.getReviews();}

    @PostMapping
    public void createReview(@RequestBody ReviewDto reviewDto){
        if(reviewDto.getRole().equals("WORKSHOP"))
            reviewService.createReviewWorkshop(Long.parseLong(reviewDto.getId()),reviewDto.getContent());
        else
            reviewService.createReviewUser(Long.parseLong(reviewDto.getId()),reviewDto.getContent());
    }
}
