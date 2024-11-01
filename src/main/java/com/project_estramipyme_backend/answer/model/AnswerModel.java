package com.project_estramipyme_backend.answer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project_estramipyme_backend.form.model.Option;
import com.project_estramipyme_backend.test.model.TestModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer_option")
public class AnswerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = true)
//    @JsonBackReference // Evita la recursión hacia Option
    private Option option;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = true)
    private TestModel test;


}
