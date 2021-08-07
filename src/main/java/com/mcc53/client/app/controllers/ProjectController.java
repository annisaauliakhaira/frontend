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
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/show")
    public String getAll(Model model) {
        model.addAttribute("datas", projectService.getAll());

        return "/project/index";
    }

    @GetMapping("/showbyid/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        model.addAttribute("project", projectService.getById(id));

        return "/project/get-by-id";
    }

    @GetMapping("/add")
    public String showForm(Model model){
        Project project = new Project();
        model.addAttribute("project", project);
        return "/project/_form";
    }

    @PostMapping("/add")
    public String saveProject(@ModelAttribute("project") Project project){
        projectService.create(project);
        return "redirect:/project/show";
    }

    @GetMapping("update/{id}")
    public String updateProject(Model model, @PathVariable("id") Long id){
        Project p = projectService.getById(id);
        model.addAttribute("project", p);

        return "/project/update";
    }

    @PostMapping("update/{id}")
    public String updateProject(Project project, @PathVariable("id") Long id){
        projectService.update(project, id);

        return "redirect:/project/show";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        projectService.delete(id);

        return "redirect:/project/show";
    }
}
