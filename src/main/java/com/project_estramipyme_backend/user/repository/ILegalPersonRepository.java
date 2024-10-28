package com.project_estramipyme_backend.user.repository;

import com.project_estramipyme_backend.user.model.LegalPersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILegalPersonRepository extends JpaRepository<LegalPersonModel, Long> {
}
