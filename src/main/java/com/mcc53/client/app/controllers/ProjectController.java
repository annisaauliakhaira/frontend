package com.mcc53.client.app.controllers;

import com.mcc53.client.app.models.Department;
import com.mcc53.client.app.models.Project;
import com.mcc53.client.app.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @GetMapping()
    public String getAll() {
        return "/project/index";
    }
}
