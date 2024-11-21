package com.project_estramipyme_backend.test.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private String name;
    private double score;
    private String recommendation;
}
