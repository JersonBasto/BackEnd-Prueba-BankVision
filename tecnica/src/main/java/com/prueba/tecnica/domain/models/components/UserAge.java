package com.prueba.tecnica.domain.models.components;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import com.prueba.tecnica.domain.models.UserModel;

@Component
public class UserAge extends AbstractMongoEventListener<UserModel> {

    public void onBeforeSave(BeforeSaveEvent<UserModel> event) {
        UserModel user = event.getSource();
        LocalDate userB = user.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period periodo = Period.between(userB, currentDate);
        event.getDocument().put("age", periodo.getYears());
    }

}
