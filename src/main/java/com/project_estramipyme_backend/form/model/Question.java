package com.project_estramipyme_backend.form.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project_estramipyme_backend.answer.model.AnswerModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statement;

    @JsonBackReference //Se usa en la parte inversa de la relación para evitar la recursión.
    @ManyToOne
    @JoinColumn(name = "form_id")
    private FormModel form;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question_Option> questionOptions; // Relación con la tabla intermedia

}