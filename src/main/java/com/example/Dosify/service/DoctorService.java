package com.example.Dosify.service;


import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;

import java.util.List;

public interface DoctorService {
    public DoctorResponseDto add_Doctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException;
    public List<DoctorResponseDto> doctorWithAppointment(int nmb);
    public List<DoctorResponseDto>doctorOfAgeGroup(int age);
    public DoctorResponseDto updateDetails(String emailId,String name);
    public DoctorResponseDto updateDetails(String emailId, int age);
    public DoctorResponseDto updateDetails(String emailId, Gender gender);
    public Double ration();
}
