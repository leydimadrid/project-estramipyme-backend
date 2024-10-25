package com.project_estramipyme_backend.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionModel {
    private Long id;
    private String statement;
    private List<OptionModel> options;
}