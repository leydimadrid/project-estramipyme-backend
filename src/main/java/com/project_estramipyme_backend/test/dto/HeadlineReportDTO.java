package com.project_estramipyme_backend.test.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class HeadlineReportDTO {
    private Long id;
    private String date;
    private String name;
    private String lastname;
    private String email;

    public HeadlineReportDTO(Long id, String date, String name, String lastname, String email) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

}
