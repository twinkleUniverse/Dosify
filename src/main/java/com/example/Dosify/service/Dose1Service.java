package com.example.Dosify.service;

import com.example.Dosify.Enum.VaccinationType;
import com.example.Dosify.model.Dose_1;
import com.example.Dosify.model.User;
import org.springframework.stereotype.Service;


public interface Dose1Service {
    public Dose_1 createDose1(VaccinationType vaccinationType, User user);
}
