package com.project_estramipyme_backend.answer.controller;

import com.project_estramipyme_backend.answer.model.AnswerModel;
import com.project_estramipyme_backend.answer.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Tag(name = "Respuestas", description = "API para gestión de respuestas de evaluación")
@RestController
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Operation(summary = "Obtener todas las respuestas",
            description = "Retorna la lista completa de respuestas registradas")
    @ApiResponse(responseCode = "200", description = "Respuestas obtenidas exitosamente")
        @GetMapping(path = "/getAnswers")
    public ArrayList<AnswerModel> getAllAnswers() {
        return this.answerService.getAllAnswers();
    }


    @Operation(summary = "Registrar nueva respuesta",
            description = "Guarda una nueva respuesta en el sistema")
    @ApiResponse(responseCode = "200", description = "Respuesta guardada exitosamente")
    @PostMapping(path = "/newAnswer")
    public AnswerModel saveAnswer(@RequestBody AnswerModel answer) {
        return this.answerService.saveAnswer(answer);
    }


    @Operation(summary = "Obtener respuesta por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Respuesta encontrada"),
            @ApiResponse(responseCode = "404", description = "Respuesta no encontrada")
    })
    @GetMapping(path = "/{id}")
    public Optional<AnswerModel> getAnswerById(
            @Parameter(description = "ID de la respuesta") @PathVariable("id") Long id) {
        return this.answerService.getAnswerById(id);
    }

}

