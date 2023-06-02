package com.festus.login.model.dto;

import com.festus.login.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private long id;
    private String studentName;
    private String email;
    private Roles roles;
}
