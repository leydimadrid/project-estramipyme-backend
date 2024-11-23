package com.project_estramipyme_backend.form.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "question_option")
public class Question_Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;


    @ManyToOne
    @JoinColumn(name = "option_id", nullable = true)
    @JsonManagedReference
    private Option option;


    @ManyToOne
    @JoinColumn(name = "question_id", nullable = true)
    @JsonBackReference
    private Question question;
}
