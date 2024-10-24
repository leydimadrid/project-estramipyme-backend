// QuestionController.java
package com.project_estramipyme_backend.question.controller;

import com.project_estramipyme_backend.question.model.QuestionModel;
import com.project_estramipyme_backend.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/getQuestions")
    public ArrayList<QuestionModel> getQuestions() {
        return this.questionService.getQuestions();
    }

    @PostMapping(path = "/newQuestion")
    public QuestionModel saveQuestion(@RequestBody QuestionModel question) {
        return this.questionService.saveQuestion(question);
    }

    @GetMapping(path = "/{id}")
    public Optional<QuestionModel> getQuestionById(@PathVariable("id") Long id) {
        return this.questionService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public QuestionModel updateQuestionById(@RequestBody QuestionModel request, @PathVariable Long id) {
        return this.questionService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean ok = this.questionService.deleteQuestion(id);
        if (ok) {
            return "Question with id " + id + " deleted!";
        } else {
            return "Error deleting question";
        }
    }
}
