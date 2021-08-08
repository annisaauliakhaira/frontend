package com.mcc53.client.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Department department;
    private User user;
    private List<Project> projects;

}
