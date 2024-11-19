package com.project_estramipyme_backend.answer.controller;

import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.answer.service.AnswerService;
import com.project_estramipyme_backend.answer.service.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping("/pdf/{id}")
    public ResponseEntity<byte[]> downloadPDF(@PathVariable("id") Long id, @RequestBody PDFRequestDTO pdfRequestDTO) {

        ByteArrayOutputStream pdfOutput = pdfService.generatePDF(id, pdfRequestDTO);

        byte[] pdfBytes = pdfOutput.toByteArray();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Reporte.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
