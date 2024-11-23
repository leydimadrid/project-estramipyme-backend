package com.project_estramipyme_backend.test.dto;

public class InfoEsquemaReoDTO {
    private Long id;
    private String name;
    private double score;


    public InfoEsquemaReoDTO(Long id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public InfoEsquemaReoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
