package com.example.Dosify.service;

import com.example.Dosify.Enum.CenterType;
import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;

import java.util.List;

public interface CenterService {
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto);
    public List<DoctorResponseDto> listOfDoctors(int centerId) throws CenterNotPresentException;
    public List<DoctorResponseDto>listOfMaleDoctors(int centerId) throws CenterNotPresentException;
    public List<DoctorResponseDto>listOfFemaleDoctors(int centerId) throws CenterNotPresentException;
    public List<DoctorResponseDto> genderDoctorsOfAge(int centerId, int age, Gender gender) throws CenterNotPresentException;
    public List<CenterResponseDto>getCenters(CenterType centerType);
}
