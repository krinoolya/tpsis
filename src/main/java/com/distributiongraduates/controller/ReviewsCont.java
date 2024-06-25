package com.distributiongraduates.controller;

import com.distributiongraduates.controller.main.Attributes;
import com.distributiongraduates.model.Reviews;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reviews")
public class ReviewsCont extends Attributes {

    @GetMapping
    public String Reviews(Model model) {
        AddAttributes(model);
        model.addAttribute("reviews", reviewsRepo.findAll());
        return "reviews";
    }

    @PostMapping("/add")
    public String ReviewsAdd(@RequestParam String review) {
        reviewsRepo.save(new Reviews(review, DateNow(), getUser()));
        return "redirect:/reviews";
    }
}
