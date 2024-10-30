package com.project_estramipyme_backend.form.repository;

import com.project_estramipyme_backend.form.model.FormModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormRepository extends JpaRepository<FormModel, Long> {
}