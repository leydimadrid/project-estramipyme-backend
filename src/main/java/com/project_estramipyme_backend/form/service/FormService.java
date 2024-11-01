package com.project_estramipyme_backend.form.service;

import com.project_estramipyme_backend.form.repository.IFormRepository;
import com.project_estramipyme_backend.form.model.FormModel;
import com.project_estramipyme_backend.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class FormService {

    @Autowired
    IFormRepository formRepository;

    public ArrayList<FormModel> getForm() {
        return (ArrayList<FormModel>) formRepository.findAll();
    }

    public FormModel saveForm(FormModel form) {
        return formRepository.save(form);
    }

    public Optional<FormModel> getById(Long id) {
        return formRepository.findById(id);
    }
    public FormModel updateById(FormModel request, Long id) {
        FormModel form = formRepository.findById(id).get();

        form.setName(request.getName());
        form.setDescription(request.getDescription());
        form.setQuestions(request.getQuestions());
        formRepository.save(form);

        return form;
    }

    public Boolean deleteForm(Long id) {
        try {
            formRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}