package com.db.mimicdb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto  {

    @NonNull
    @NotBlank
    private String name;
    @NonNull
    @NotBlank
    private String phone;
    @NonNull
    @NotBlank
    @Email(message="Please enter a valid email")
    private String email;

}
