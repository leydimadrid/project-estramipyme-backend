package com.project_estramipyme_backend.test.controller;


import com.project_estramipyme_backend.test.model.TestModel;
import com.project_estramipyme_backend.test.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Tag(name = "Tests", description = "API para gestión de tests de evaluación")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    } //Fin inyección de dependencias

    @Operation(summary = "Obtener todos los tests",
            description = "Retorna la lista de todos los tests realizados")
    @ApiResponse(responseCode = "200", description = "Tests obtenidos exitosamente")
    @GetMapping(path = "/getTest")
    public ArrayList<TestModel> getTest() {
        return this.testService.getTest();
    }


    @Operation(summary = "Crear nuevo test",
            description = "Registra un nuevo test en el sistema")
    @ApiResponse(responseCode = "200", description = "Test creado exitosamente")
    @PostMapping(path = "/newTest")
    public TestModel saveTest(@RequestBody TestModel test) {
        return this.testService.saveTest(test);
    }


    @Operation(summary = "Obtener test por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Test encontrado"),
            @ApiResponse(responseCode = "404", description = "Test no encontrado")
    })
    @GetMapping(path = "/{id}")
    public Optional<TestModel> getUserById(
            @Parameter(description = "ID del test") @PathVariable("id") Long id) {
        return this.testService.getById(id);
    }





}
