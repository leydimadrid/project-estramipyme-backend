// QuestionService.java
package com.project_estramipyme_backend.question.service;

import com.project_estramipyme_backend.question.repository.IQuestionRepository;
import com.project_estramipyme_backend.question.model.QuestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    IQuestionRepository questionRepository;

    public ArrayList<QuestionModel> getQuestions() {
        return (ArrayList<QuestionModel>) questionRepository.findAll();
    }

    public QuestionModel saveQuestion(QuestionModel question) {
        return questionRepository.save(question);
    }

    public Optional<QuestionModel> getById(Long id) {
        return questionRepository.findById(id);
    }

    public QuestionModel updateById(QuestionModel request, Long id) {
        QuestionModel question = questionRepository.findById(id).get();
        question.setStatement(request.getStatement());
        return questionRepository.save(question);
    }

    public Boolean deleteQuestion(Long id) {
        try {
            questionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
