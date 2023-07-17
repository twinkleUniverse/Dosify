package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.VaccinationType;
import com.example.Dosify.model.Dose_1;
import com.example.Dosify.model.User;
import com.example.Dosify.service.Dose1Service;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class Dose1ServiceImpl implements Dose1Service {
    @Override
    public Dose_1 createDose1(VaccinationType vaccinationType, User user) {
        Dose_1 dose1= Dose_1.builder()
                      .doseId(String.valueOf(UUID.randomUUID()))
                      .vaccinationType(vaccinationType)
                      .user(user)
                      .build();
        return dose1;
    }
}
