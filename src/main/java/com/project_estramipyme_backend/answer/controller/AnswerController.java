package com.project_estramipyme_backend.answer.controller;

import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.answer.service.AnswerService;
import com.project_estramipyme_backend.answer.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;
    private final PDFService pdfService;

    @Autowired
    public AnswerController(AnswerService answerService, PDFService pdfService) {
        this.answerService = answerService;
        this.pdfService = pdfService;
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

    @GetMapping(path = "/generatePdf")
    public ResponseEntity<byte[]> generatePdf() {
        List<AnswerModel> answers = this.answerService.getAllAnswers();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            pdfService.generatePdf(answers, baos);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=answers.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(baos.toByteArray());

        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
