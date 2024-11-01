package com.project_estramipyme_backend.answer.service;

import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.answer.repository.IAnswerRepository;
import com.project_estramipyme_backend.form.model.FormModel;
import com.project_estramipyme_backend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private final IAnswerRepository answerRepository;

    @Autowired
    public AnswerService(final IAnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public ArrayList<AnswerModel> getAllAnswers() {
        return (ArrayList<AnswerModel>) answerRepository.findAll();
    }

    public Optional<AnswerModel> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    public AnswerModel saveAnswer(AnswerModel answerModel) {
        return answerRepository.save(answerModel);
    }


}
