package com.project_estramipyme_backend.answer.service;

import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.answer.repository.AnswerRepository;
import com.project_estramipyme_backend.answer.repository.IAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private final IAnswerRepository answerRepository;

    @Autowired
     public AnswerService(final IAnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<AnswerModel> getAllAnswers() {
        return answerRepository.findAll();
    }
    public Optional<AnswerModel> getAnswerById(Long id) {
         return answerRepository.findById();
    }
    public AnswerModel saveAnswer(AnswerModel answerModel) {
        return answerRepository.save(answerModel);
    }
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}
