// IQuestionRepository.java
package com.project_estramipyme_backend.question.repository;

import com.project_estramipyme_backend.question.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends JpaRepository<QuestionModel, Long> {
}