package com.project_estramipyme_backend.user.controller;

import com.project_estramipyme_backend.user.model.LegalPersonModel;
import com.project_estramipyme_backend.user.service.LegalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/legalPersons")
public class LegalPersonController {

    private final LegalPersonService legalPersonService;

    @Autowired
    public LegalPersonController(LegalPersonService legalPersonService) {
        this.legalPersonService = legalPersonService;
    } // Fin inyección de dependencias

    @GetMapping(path = "/getLegalPersons")
    public ArrayList<LegalPersonModel> getLegalPersons() {
        return this.legalPersonService.getLegalPersons();
    }

    @PostMapping(path = "/newLegalPerson")
    public LegalPersonModel saveLegalPerson(@RequestBody LegalPersonModel legalPerson) {
        return this.legalPersonService.saveLegalPerson(legalPerson);
    }

    @GetMapping(path = "/{id}")
    public Optional<LegalPersonModel> getLegalPersonById(@PathVariable("id") Long id) {
        return this.legalPersonService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public LegalPersonModel updateLegalPersonById(@RequestBody LegalPersonModel request, @PathVariable Long id) {
        return this.legalPersonService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean isOk = this.legalPersonService.deleteLegalPerson(id);

        if (isOk) {
            return "Legal person with id " + id + " deleted!";
        } else {
            return "Error occurred while deleting legal person with id " + id;
        }
    }
}
