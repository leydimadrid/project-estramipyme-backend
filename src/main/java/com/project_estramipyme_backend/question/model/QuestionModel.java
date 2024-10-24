
// QuestionModel.java
package com.project_estramipyme_backend.question.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import com.project_estramipyme_backend.option.model.OptionModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class QuestionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String statement;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)

    private List<OptionModel> options;
}