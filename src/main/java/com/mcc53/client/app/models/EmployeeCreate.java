package com.mcc53.client.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreate {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Long department_id;
    private String username;
    private String password;
}
