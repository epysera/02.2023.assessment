package com.epysera.assessment.service;

import com.epysera.assessment.entity.Employee;
import com.epysera.assessment.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeesRepository employeesRepository;

    public List<Employee> listAll() {
        return employeesRepository.findAll();
    }

    public void save(Employee employee) {
        employeesRepository.save(employee);
    }
    public Employee get(Long id) {
        return employeesRepository.findById(id).get();
    }
    public void delete(long id) {
        employeesRepository.deleteById(id);
    }
}
