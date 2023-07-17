package com.example.Dosify.service;

import com.example.Dosify.Enum.VaccinationType;
import com.example.Dosify.model.Dose_2;
import com.example.Dosify.model.User;

public interface Dose2Service {
    public Dose_2 createDose2(VaccinationType vaccinationType, User user);
}
