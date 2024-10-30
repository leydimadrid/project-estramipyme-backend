package com.project_estramipyme_backend.answer.model;

import com.project_estramipyme_backend.form.model.FormModel;
import com.project_estramipyme_backend.form.model.Option;
import com.project_estramipyme_backend.form.model.Question;
import com.project_estramipyme_backend.form.model.Test;
import com.project_estramipyme_backend.user.model.UserModel;
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
    private Option option;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = true)
    private Test test;


}
