package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.VaccinationType;
import com.example.Dosify.model.Dose_1;
import com.example.Dosify.model.Dose_2;
import com.example.Dosify.model.User;
import com.example.Dosify.service.Dose2Service;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Dose2ServiceImpl implements Dose2Service {

    public Dose_2 createDose2(VaccinationType vaccinationType, User user) {
        Dose_2 dose2 = Dose_2.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccinationType(vaccinationType)
                .user(user)
                .build();
        return dose2;
    }
}
