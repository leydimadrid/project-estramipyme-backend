package com.project_estramipyme_backend.test.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TestRequestDTO {
    private Long id;
    private LocalDateTime date;
    private String userEmail;
    private List<Long> answers_option_ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public List<Long> getAnswers_option_ids() {
        return answers_option_ids;
    }

    public void setAnswers_option_ids(List<Long> answers_option_ids) {
        this.answers_option_ids = answers_option_ids;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
