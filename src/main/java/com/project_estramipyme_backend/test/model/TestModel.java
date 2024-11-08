package com.project_estramipyme_backend.test.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.form.model.Question;
import com.project_estramipyme_backend.user.model.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="test")
public class TestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel users;

//    @OneToMany(mappedBy = "test")  // Define la relación con AnswerOption
//    private List<AnswerModel> answers;
}
