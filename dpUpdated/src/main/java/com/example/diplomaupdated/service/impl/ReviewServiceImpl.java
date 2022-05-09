package com.example.diplomaupdated.service.impl;

import com.example.diplomaupdated.model.Review;
import com.example.diplomaupdated.model.User;
import com.example.diplomaupdated.model.Workshop;
import com.example.diplomaupdated.repo.ReviewRepo;
import com.example.diplomaupdated.repo.UserRepo;
import com.example.diplomaupdated.repo.WorkshopRepo;
import com.example.diplomaupdated.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final UserRepo userRepo;
    private final WorkshopRepo workshopRepo;

    @Override
    public List<Review> getReviews() {
        return reviewRepo.findAll();
    }

    @Override
    public void createReviewUser(Long user_id,String content) {
        Review currentReview = new Review();
        System.out.println(user_id);

        Optional<User> user = userRepo.findById(user_id);
        if(user.isEmpty()){
            throw new IllegalStateException("No such user");
        }


        currentReview.setUser(userRepo.getById(user_id));
        currentReview.setContent(content);

        reviewRepo.save(currentReview);
    }

    @Override
    public void createReviewWorkshop(Long workshop_id, String content) {
        Review currentReview = new Review();
        System.out.println(workshop_id);

        Optional<Workshop> workshop = workshopRepo.findById(workshop_id);
        if(workshop.isEmpty()){
            throw new IllegalStateException("No such workshop");
        }


        currentReview.setWorkshop(workshopRepo.getById(workshop_id));
        currentReview.setContent(content);

        reviewRepo.save(currentReview);
    }

    @Override
    public void deleteReview(Long id) {
        Optional<Review> currentReview = reviewRepo.findById(id);
        if(currentReview.isEmpty())
            throw new IllegalStateException("Review with id" + id + "does not exist");

        Review review = reviewRepo.getById(id);

        reviewRepo.delete(review);

    }
}
