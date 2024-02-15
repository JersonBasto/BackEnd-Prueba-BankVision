package com.prueba.tecnica.domain.dtos;

import lombok.Data;

@Data
public class ResLoginDto {
    private String JWT;
    private boolean state;
}
