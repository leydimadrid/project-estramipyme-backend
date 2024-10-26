package com.project_estramipyme_backend.answer.controller;

import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.answer.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }
    @GetMapping
    public ResponseEntity<List<AnswerModel>> getAllAnswers() {
        List<AnswerModel> answers = answerService.getAllAnswers();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AnswerModel> getAnswerById(@PathVariable Long id) {
        Optional<AnswerModel> answer = answerService.getAnswerById(id);
        return answer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AnswerModel> addAnswer(@RequestBody AnswerModel answer) {
        AnswerModel savedAnswer = answerService.saveAnswer(new AnswerModel());
        return new ResponseEntity<>(savedAnswer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerModel> updateAnswer(@PathVariable Long id, @RequestBody AnswerModel answerModel) {
        answerModel.setId(id); // Asegura que la respuesta a actualizar tenga el ID correcto
        AnswerModel updatedAnswer = answerService.saveAnswer(answerModel);
        return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
