package com.project_estramipyme_backend.answer.repository;

import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerRepository extends JpaRepository<AnswerModel, Long> {
}
