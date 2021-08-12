package com.mcc53.client.app.services;

import com.mcc53.client.app.models.Department;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

@Service
public class DepartmentService {
    private RestTemplate restTemplate;

    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.baseUrl}/department")
    private String baseUrl;

    public List<Department> getAll() {
        ResponseEntity<List<Department>> res = restTemplate
                .exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(createHeaders()),
                        new ParameterizedTypeReference<List<Department>>(){});

        return res.getBody();
    }

    public Department getById(Long id){
        ResponseEntity<Department> res = restTemplate
                .getForEntity(baseUrl+"/"+ id, Department.class);

        return res.getBody();
    }

    public Department create(Department department){
        ResponseEntity<Department> res = restTemplate
                .postForEntity(baseUrl, department, Department.class);

        return res.getBody();
    }

    public String update(Department department, Long id){
        restTemplate.put(baseUrl+"/"+id, department, Department.class);
        return "done";
    }

    public String delete(Long id){
        restTemplate.delete(baseUrl+"/"+id, Department.class);
        return "success";
    }

    private HttpHeaders createHeaders(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String password = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();

        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};

    }

}
