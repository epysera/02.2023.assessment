package com.epysera.assessment;

import com.epysera.assessment.controller.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class Employees2Application implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Employees2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		String sql = "SELECT * FROM EMPLOYEES";

		List<Employee> employees = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(Employee.class));
		employees.forEach(System.out :: println);
	}

}
