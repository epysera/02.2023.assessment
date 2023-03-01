package com.epysera.assessment.controller;
import com.epysera.assessment.entity.Employee;
import com.epysera.assessment.service.EmployeeService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class HomeController {

    private final EmployeeService service;

    public HomeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Employee> listEmployees = service.listAll();
        model.addAttribute("listEmployees", listEmployees);

        return "index";
    }
    @RequestMapping("/new")
    public String showNewEmployeePage(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "new_employee";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        service.save(employee);

        return "redirect:/";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditEmployeePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_employee");
        Employee employee = service.get((long) id);
        mav.addObject("employee", employee);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }


    @Secured("USER")
    @RequestMapping("/user")

    public String userView() {
        return "userTemp";
    }
    @Secured("ADMIN")
    @RequestMapping("/admin")

        public String adminView() { return "adminTemp"; }
    }