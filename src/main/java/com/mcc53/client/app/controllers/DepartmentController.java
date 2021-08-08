package com.mcc53.client.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/department")
public class DepartmentController {

    @GetMapping()
    public String getAll() {
        return "/department/index";
    }

}
