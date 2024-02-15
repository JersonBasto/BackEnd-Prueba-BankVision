package com.prueba.tecnica.domain.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private String identificationNumber;
    private String password;
}
