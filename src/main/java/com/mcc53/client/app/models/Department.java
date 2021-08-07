package com.mcc53.client.app.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Department {
    private Long id;
    private String name;
}
