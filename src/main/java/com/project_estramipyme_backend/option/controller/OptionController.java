package com.project_estramipyme_backend.option.controller;

import com.project_estramipyme_backend.option.model.OptionModel;
import com.project_estramipyme_backend.option.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/options")
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }


    @GetMapping(path = "/getOptions")
    public ArrayList<OptionModel> getOptions() {
        return this.optionService.getOptions();
    }


    @PostMapping(path = "/newOption")
    public OptionModel saveOption(@RequestBody OptionModel option) {
        return this.optionService.saveOption(option);
    }


    @GetMapping(path = "/{id}")
    public Optional<OptionModel> getOptionById(@PathVariable("id") Long id) {
        return this.optionService.getById(id);
    }


    @PutMapping(path = "/{id}")
    public OptionModel updateOptionById(@RequestBody OptionModel request, @PathVariable Long id) {
        return this.optionService.updateById(request, id);
    }


    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean ok = this.optionService.deleteOption(id);
        if (ok) {
            return "Option with id " + id + " deleted!";
        } else {
            return "Error deleting option";
        }
    }
}
