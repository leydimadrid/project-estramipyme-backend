package com.project_estramipyme_backend.test.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TestRequestDTO {
    private Long id;
    private LocalDateTime date;
    private Long user_id;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<Long> getAnswers_option_ids() {
        return answers_option_ids;
    }

    public void setAnswers_option_ids(List<Long> answers_option_ids) {
        this.answers_option_ids = answers_option_ids;
    }
}
