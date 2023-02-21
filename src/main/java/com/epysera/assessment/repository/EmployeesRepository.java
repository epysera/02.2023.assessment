package com.epysera.assessment.repository;

import com.epysera.assessment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository <Employee, Long> {

}
