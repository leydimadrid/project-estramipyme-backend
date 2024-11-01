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

    public AnswerModel updateById(AnswerModel request, Long id) {
        AnswerModel answer = answerRepository.findById(id).get();

        answer.setTest(request.getTest());
        answer.setOption(request.getOption());
        answer.setOption(answer.getOption());
        answerRepository.save(answer);

        return answer;
    }

    public Boolean deleteAnswer(Long id) {
        try {
            answerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
