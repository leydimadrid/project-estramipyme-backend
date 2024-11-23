package com.project_estramipyme_backend.form.service;

import com.project_estramipyme_backend.form.repository.IFormRepository;
import com.project_estramipyme_backend.form.model.FormModel;
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

    public Optional<FormModel> getById(Long id) {
        return formRepository.findById(id);
    }

}