package com.project_estramipyme_backend.test.repository;

import com.project_estramipyme_backend.test.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestRepository extends JpaRepository<TestModel, Long> {
}