package com.mcc53.client.app.controllers;

import com.mcc53.client.app.models.Department;
import com.mcc53.client.app.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public String getAll() {
        return "/department/index";
    }

    @GetMapping("/get-all")
    public @ResponseBody List<Department> getAllService(){
        return departmentService.getAll();
    }
}
