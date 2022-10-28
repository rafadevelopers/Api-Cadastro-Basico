package com.example.rest.rest.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UserForm {

    @NotBlank(message = "Nome não pode estar em branco")
    private String name;
    @NotBlank(message = "Edereço de e-mail invalido")
    private String email;
    @NotBlank(message = "Numero de CPF inválido")
    private String cpf;
    @NotNull(message = "Data de nascimento não pode ser nula")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;




}
