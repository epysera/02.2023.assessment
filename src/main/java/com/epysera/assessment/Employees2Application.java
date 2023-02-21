package com.epysera.assessment;

import com.epysera.assessment.entity.Employee;
import com.epysera.assessment.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Employees2Application implements CommandLineRunner {
	@Autowired
	private EmployeesRepository employeesRepository;

	public static void main(String[] args) {
		SpringApplication.run(Employees2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		List<Employee> listEmployees = employeesRepository.findAll();

		listEmployees.forEach(System.out :: println);
	}

}
