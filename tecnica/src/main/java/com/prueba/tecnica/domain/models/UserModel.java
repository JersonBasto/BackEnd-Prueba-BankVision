package com.prueba.tecnica.domain.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class UserModel {
    @Id
    private String id;
    private String name;
    private String lastName;
    @Indexed(unique = true)
    private String phone;
    private Date birthDate;
    private Number age;
    private String idtype;
    @Indexed(unique = true)
    private String email;
    private String password;
    private boolean IsDelete;
    @Indexed(unique = true)
    private String identificationNumber;

    public boolean getIsDelete() {
        return this.IsDelete;
    }
}
