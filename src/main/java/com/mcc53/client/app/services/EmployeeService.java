package com.mcc53.client.app.services;


import com.mcc53.client.app.models.Department;
import com.mcc53.client.app.models.Employee;
import com.mcc53.client.app.models.ResponseMessage;
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

    @Value("${api.baseUrl}/show")
    private String baseUrl;

    public List<Employee> getAll() {
        ResponseEntity<List<Employee>> res = restTemplate
                .exchange(baseUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Employee>>(){});

        return res.getBody();
    }
}
