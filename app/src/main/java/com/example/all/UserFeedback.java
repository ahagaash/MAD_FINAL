package com.example.all;

import android.widget.RatingBar;
import android.widget.TextView;

public class UserFeedback {
    String fullname;
    Integer mobile;
    String email;
    String reviews;
    float ratingBar;
    float ratingBar1;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    float total;
    float average;


    public float getRatingBar1() {
        return ratingBar1;
    }

    public void setRatingBar1(float ratingBar1) {
        this.ratingBar1 = ratingBar1;
    }

    public float getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(float ratingBar) {
        this.ratingBar = ratingBar;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}