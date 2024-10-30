package com.project_estramipyme_backend.answer.model;

import com.project_estramipyme_backend.formPrincipal.FormularioModel;
import com.project_estramipyme_backend.formPrincipal.OptionModel;
import com.project_estramipyme_backend.formPrincipal.QuestionModel;
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
@Table(name = "answer")
public class AnswerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionModel question;

    @ManyToOne
    @JoinColumn(name = "form_id")
    private FormularioModel formulario;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = true)
    private OptionModel option;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private UserModel user;


}
