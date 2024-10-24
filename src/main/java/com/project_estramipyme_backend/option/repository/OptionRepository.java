package com.project_estramipyme_backend.option.repository;

import com.project_estramipyme_backend.option.model.OptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOptionRepository extends JpaRepository<OptionModel, Long> {

}
