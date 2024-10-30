package com.project_estramipyme_backend.form.controller;

import com.project_estramipyme_backend.form.model.FormModel;
import com.project_estramipyme_backend.form.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/forms")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class FormController {

    @Autowired
    private FormService formService;

    // GET - Obtener todos los formularios
    @GetMapping(path = "/getForm")
    public ArrayList<FormModel> getForms() {
        return formService.getForms();
    }

    // GET - Obtener un formulario específico
    @GetMapping(path = "/{id}")
    public FormModel getFormById(@PathVariable("id") Long id) {
        return formService.getFormById(id);
    }

    // POST - Crear un nuevo formulario
    @PostMapping(path = "/newForm")
    public FormModel saveForm(@RequestBody FormModel form) {
        return formService.saveForm(form);
    }
}
