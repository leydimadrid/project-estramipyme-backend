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

    @PostMapping(path = "/newForm")
    public FormModel saveForm(@RequestBody FormModel form) {
        return this.formService.saveForm(form);
    }

    @GetMapping(path = "/{id}")
    public Optional<FormModel> getFormById(@PathVariable("id") Long id) {
        return this.formService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public FormModel updateFormById(@RequestBody FormModel request, @PathVariable Long id) {
        return this.formService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean isOk = this.formService.deleteForm(id);

        if (isOk) {
            return "Form with id " + id + " delete!";
        } else {
            return "Error";
        }
    }
}
