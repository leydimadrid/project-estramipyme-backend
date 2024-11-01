package com.project_estramipyme_backend.answer.controller;

import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.answer.service.AnswerService;
import com.project_estramipyme_backend.form.model.FormModel;
import com.project_estramipyme_backend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }


    @GetMapping(path = "/getAnswers")
    public ArrayList<AnswerModel> getAllAnswers() {
        return this.answerService.getAllAnswers();
    }

    @PostMapping(path = "/newAnswer")
    public AnswerModel saveAnswer(@RequestBody AnswerModel answer) {
        return this.answerService.saveAnswer(answer);
    }

    @GetMapping(path = "/{id}")
    public Optional<AnswerModel> getAnswerById(@PathVariable("id") Long id) {
        return this.answerService.getAnswerById(id);
    }

}

