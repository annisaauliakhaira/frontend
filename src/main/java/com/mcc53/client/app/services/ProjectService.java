package com.mcc53.client.app.services;

import com.mcc53.client.app.models.Department;
import com.mcc53.client.app.models.Employee;
import com.mcc53.client.app.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProjectService {
    private RestTemplate restTemplate;

    @Autowired
    public ProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.baseUrl}/project")
    private String baseUrl;


    public List<Project> getAll() {
        ResponseEntity<List<Project>> res = restTemplate
                .exchange(baseUrl+"/show", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Project>>(){});

        return res.getBody();
    }

    public Project getById(Long id){
        ResponseEntity<Project> res = restTemplate
                .getForEntity(baseUrl+"/showbyid/"+ id, Project.class);

        return res.getBody();
    }

    public Project create(Project project){
        ResponseEntity<Project> res = restTemplate
                .postForEntity(baseUrl+"/create", project, Project.class);

        return res.getBody();
    }

    public String update(Project project, Long id){
        restTemplate.put(baseUrl+"/update/"+id, project, Project.class);
        return "done";
    }

    public String delete(Long id){
        restTemplate.delete(baseUrl+"/delete/"+id, Project.class);
        return "success";
    }

}
