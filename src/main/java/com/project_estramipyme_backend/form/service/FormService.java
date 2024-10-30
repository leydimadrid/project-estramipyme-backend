package com.project_estramipyme_backend.form.service;

import com.project_estramipyme_backend.form.repository.IFormRepository;
import com.project_estramipyme_backend.form.model.FormModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class FormService {

    @Autowired
    IFormRepository formRepository;

    // GET - Obtener todos los formularios
    public ArrayList<FormModel> getForms() {
        return (ArrayList<FormModel>) formRepository.findAll();
    }

    // GET - Obtener un formulario por ID
    public FormModel getFormById(Long id) {
        return formRepository.findById(id).orElse(null);
    }

    // POST - Guardar un nuevo formulario
    public FormModel saveForm(FormModel form) {
        return formRepository.save(form);
    }
}