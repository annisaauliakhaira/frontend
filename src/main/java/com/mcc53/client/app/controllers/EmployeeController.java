package com.mcc53.client.app.controllers;

import com.mcc53.client.app.models.EmployeeCreate;
import com.mcc53.client.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @GetMapping()
    public String getAll() {
        return "/employee/index";
    }


}
