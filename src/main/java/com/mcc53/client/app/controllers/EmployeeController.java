package com.mcc53.client.app.controllers;

import com.mcc53.client.app.models.Department;
import com.mcc53.client.app.models.Employee;
import com.mcc53.client.app.models.EmployeeCreate;
import com.mcc53.client.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/showbyid/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        model.addAttribute("employee", employeeService.getById(id));

        return "/employee/get-by-id";
    }

    @GetMapping("/show")
    public String getAll(Model model) {
        model.addAttribute("datas", employeeService.getAll());

        return "/employee/index";
    }

    @GetMapping("/add")
    public String showForm(Model model){
        EmployeeCreate employee = new EmployeeCreate();
        model.addAttribute("employee", employee);
        return "/employee/_form";
    }

    @PostMapping("/add")
    public String saveProject(@ModelAttribute("employee") EmployeeCreate employee){
        employeeService.create(employee);
        return "redirect:/employee/show";
    }


}
