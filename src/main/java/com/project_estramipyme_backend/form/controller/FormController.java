package com.project_estramipyme_backend.form.controller;

import com.project_estramipyme_backend.form.model.FormModel;
import com.project_estramipyme_backend.form.service.FormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@Tag(name = "Formularios", description = "API para gestión de formularios de evaluación")
@RestController
@RequestMapping("/api/form")
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class FormController {

    @Autowired
    private FormService formService;

    @Operation(summary = "Obtener todos los formularios")
    @ApiResponse(responseCode = "200", description = "Formularios obtenidos exitosamente")
    @GetMapping(path = "/getForm")
    public ArrayList<FormModel> getForm() {
        return this.formService.getForm();
    }



    @Operation(summary = "Obtener formulario por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Formulario encontrado"),
            @ApiResponse(responseCode = "404", description = "Formulario no encontrado")

    })

    @GetMapping(path = "/{id}")
    public Optional<FormModel> getFormById(
            @Parameter(description = "ID del formulario") @PathVariable("id") Long id) {
        return this.formService.getById(id);
    }


    }



