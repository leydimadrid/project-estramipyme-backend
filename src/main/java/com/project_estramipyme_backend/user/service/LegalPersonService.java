package com.project_estramipyme_backend.user.service;

import com.project_estramipyme_backend.user.repository.ILegalPersonRepository;
import com.project_estramipyme_backend.user.model.LegalPersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LegalPersonService {

    @Autowired
    ILegalPersonRepository legalPersonRepository;

    public ArrayList<LegalPersonModel> getLegalPersons() {
        return (ArrayList<LegalPersonModel>) legalPersonRepository.findAll();
    }

    public LegalPersonModel saveLegalPerson(LegalPersonModel legalPerson) {
        return legalPersonRepository.save(legalPerson);
    }

    public Optional<LegalPersonModel> getById(Long id) {
        return legalPersonRepository.findById(id);
    }

    public LegalPersonModel updateById(LegalPersonModel request, Long id) {
        LegalPersonModel legalPerson = legalPersonRepository.findById(id).orElseThrow(() -> new NoSuchElementException("LegalPerson not found"));

        legalPerson.setEmail(request.getEmail());
        legalPerson.setPassword(request.getPassword());
        legalPerson.setBusinessName(request.getBusinessName());
        legalPerson.setLegalRepresentative(request.getLegalRepresentative());
        legalPerson.setTypeDocument(request.getTypeDocument());
        legalPerson.setNumberDocument(request.getNumberDocument());

        legalPersonRepository.save(legalPerson);

        return legalPerson;
    }

    public Boolean deleteLegalPerson(Long id) {
        try {
            legalPersonRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
