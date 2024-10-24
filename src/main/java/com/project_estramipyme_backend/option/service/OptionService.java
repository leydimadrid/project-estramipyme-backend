package com.project_estramipyme_backend.option.service;

import com.project_estramipyme_backend.option.repository.IOptionRepository;
import com.project_estramipyme_backend.option.model.OptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class OptionService {

    @Autowired
    IOptionRepository optionRepository;


    public ArrayList<OptionModel> getOptions() {
        return (ArrayList<OptionModel>) optionRepository.findAll();
    }


    public OptionModel saveOption(OptionModel option) {
        return optionRepository.save(option);
    }


    public Optional<OptionModel> getById(Long id) {
        return optionRepository.findById(id);
    }


    public OptionModel updateById(OptionModel request, Long id) {
        OptionModel option = optionRepository.findById(id).get();
        option.setText(request.getText());
        option.setValue(request.getValue());
        return optionRepository.save(option);
    }


    public Boolean deleteOption(Long id) {
        try {
            optionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}