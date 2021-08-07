package com.mcc53.client.app.controllers;

import com.mcc53.client.app.models.Department;
import com.mcc53.client.app.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getAll(Model model) {
        model.addAttribute("datas", departmentService.getAll());

        return "/department/index";
    }

    @GetMapping("/get-all")
    public @ResponseBody List<Department> getAll() {
        return departmentService.getAll();
    }

    @GetMapping("{id}")
    public @ResponseBody Department getById(@PathVariable("id") Long id){
        return departmentService.getById(id);
    }

    @PostMapping("/create")
    public @ResponseBody Department createDepartment(@RequestBody Department department){
        return departmentService.create(department);
    }

    @PutMapping("/{id}")
    public @ResponseBody String update(@PathVariable("id") Long id, @RequestBody Department department) {
        return departmentService.update(department, id);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable("id") Long id) {
        return departmentService.delete(id);
    }


//    @GetMapping("/add")
//    public String showForm(Model model){
//        Department department = new Department();
//        model.addAttribute("departmentRequest", department);
//        return "/department/_form";
//    }
//
//    @PostMapping("/add")
//    public String saveProject(@ModelAttribute("department") Department department){
//        departmentService.create(department);
//        return "redirect:/department/show";
//    }

//    @GetMapping("update/{id}")
//    public String updateDepartment(Model model, @PathVariable("id") Long id){
//        Department d = departmentService.getById(id);
//        model.addAttribute("department", d);
//
//        return "/department/update";
//    }
//
//    @PostMapping("update/{id}")
//    public String updateDepartment(Department department, @PathVariable("id") Long id){
//        departmentService.update(department, id);
//
//        return "redirect:/department/show";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id){
//        departmentService.delete(id);
//
//        return "redirect:/department/show";
//    }
}
