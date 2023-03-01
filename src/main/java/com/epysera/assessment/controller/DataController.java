package com.epysera.assessment.controller;

import com.epysera.assessment.entity.Employee;
import com.epysera.assessment.service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DataController {

    private final EmployeeService service;

    public DataController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String fileName = "employees " + currentDateTime +".csv";

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;

        response.setHeader(headerKey, headerValue);

        List<Employee> employeeList = service.listAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"ID", "Imie", "Nazwisko", "E-mail", "Telefon", "Data zatrudnienia", "Pensja"};
        String[] nameMapping = {"id","name","surname", "email", "phone", "employment_date", "salary"};

        csvWriter.writeHeader(csvHeader);

        for (Employee employee : employeeList) {
            csvWriter.write(employee, nameMapping);
        }
        csvWriter.close();

    }


}
