package com.example.Dosify.transformer;

import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorTransformer {

    public static Doctor doctorRequestDtoToDoctor(DoctorRequestDto doctorRequestDto){
        return Doctor.builder()
                .name(doctorRequestDto.getName())
                .age(doctorRequestDto.getAge())
                .gender(doctorRequestDto.getGender())
                .emailId(doctorRequestDto.getEmailId())
                .mobNo(doctorRequestDto.getMobNo())
                .build();
    }
    public static DoctorResponseDto doctorResponseDto(Doctor doctor){
        CenterResponseDto centerResponseDto=CenterTransformer.centerToCenterResponseDto(doctor.getVaccinationCenter());
        return DoctorResponseDto.builder()
                .name(doctor.getName())
                .mobNo(doctor.getMobNo())
                .emailId(doctor.getEmailId())
                .centerResponseDto(centerResponseDto)
                .build();
    }
    public static List<DoctorResponseDto> doctorResponseDto(List<Doctor>doctorList){
        List<DoctorResponseDto>doctorResponseDtoList=new ArrayList<>();
        for(Doctor doc:doctorList){
            doctorResponseDtoList.add(doctorResponseDto(doc));
        }
        return doctorResponseDtoList;
    }
}
