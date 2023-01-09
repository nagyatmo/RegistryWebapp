package com.release.iktatoapi.data.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserModel {
    @NotNull(message = "Please enter email")
    @Email(message = "Please enter valid email")
    private String email;
    @NotNull(message="Please enter password")
    @Size(min=5 , message ="password should be atleast 5 characters")
    private String password;
}
