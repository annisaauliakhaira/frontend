package com.mcc53.client.app.services;

import com.mcc53.client.app.models.Department;
import com.mcc53.client.app.models.Employee;
import com.mcc53.client.app.models.EmployeeCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeService {
    private RestTemplate restTemplate;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.baseUrl}/employee")
    private String baseUrl;

    @Value("${api.baseUrl}/user")
    private String baseUrl2;

    public List<Employee> getAll() {
        ResponseEntity<List<Employee>> res = restTemplate
                .exchange(baseUrl+"/show", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Employee>>(){});

        return res.getBody();
    }

    public Employee getById(Long id){
        ResponseEntity<Employee> res = restTemplate
                .getForEntity(baseUrl +"/showbyid/"+ id, Employee.class);

        return res.getBody();
    }

    public EmployeeCreate create(EmployeeCreate employee){
        ResponseEntity<EmployeeCreate> res = restTemplate
                .postForEntity(baseUrl2+"/register", employee, EmployeeCreate.class);

        return res.getBody();
    }

    public String update(EmployeeCreate employee, Long id){
        restTemplate.put(baseUrl+"/update/"+id, employee, EmployeeCreate.class);
        return "done";
    }

    public String delete(Long id){
        restTemplate.delete(baseUrl+"/delete/"+id, Employee.class);
        return "success";
    }
}
