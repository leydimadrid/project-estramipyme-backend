package com.project_estramipyme_backend.option.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.project_estramipyme_backend.question.model.QuestionModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "options")
public class OptionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    private Integer value;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionModel question;
}