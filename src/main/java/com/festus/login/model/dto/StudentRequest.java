package com.festus.login.model.dto;

import com.festus.login.model.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    @NotNull
    @NotBlank
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private Roles roles;
}
