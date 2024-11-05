package com.project_estramipyme_backend.form.controller;

import com.project_estramipyme_backend.form.model.FormModel;
import com.project_estramipyme_backend.form.service.FormService;
import com.project_estramipyme_backend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/form")
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class FormController {

    @Autowired
    private FormService formService;

    @GetMapping(path = "/getForm")
    public ArrayList<FormModel> getForm() {
        return this.formService.getForm();
    }

    @GetMapping(path = "/{id}")
    public Optional<FormModel> getFormById(@PathVariable("id") Long id) {
        return this.formService.getById(id);
    }


}
