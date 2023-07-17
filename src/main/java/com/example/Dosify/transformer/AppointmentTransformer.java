package com.example.Dosify.transformer;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.Enum.VaccinationType;
import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.model.Appointment;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.Dose_1;
import com.example.Dosify.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AppointmentTransformer {
    public  static Appointment appointmentRequestDtoToAppointment(AppointmentRequestDto appointmentRequestDto, User user, Doctor doctor){
          return Appointment.builder()
                  .AppointmentNo(String.valueOf(UUID.randomUUID()))
                  .doseNo(appointmentRequestDto.getDoseNo())
                  .user(user)
                  .doctor(doctor)
                  .build();
    }
    public static AppointmentResponseDto appointToAppointmentResponseDto(Appointment appointment, User user, Doctor doctor, VaccinationType vaccinationType){
        CenterResponseDto centerResponseDto=CenterTransformer.centerToCenterResponseDto(appointment.getDoctor().getVaccinationCenter());
        return AppointmentResponseDto.builder()
                .userName(user.getName())
                .appontmentNo(appointment.getAppointmentNo())
                .appointmentDate(appointment.getAppointment_Date())
                .doseNo(appointment.getDoseNo())
                .centerResponseDto(centerResponseDto)
                .doctorName(doctor.getName())
                .vaccinationType(vaccinationType)
                .build();
    }
    public static List<AppointmentResponseDto> appointToAppointmentResponseDto(List<Appointment> appointmentList){
       List<AppointmentResponseDto> appointmentResponseDtoList=new ArrayList<>();
        for(Appointment appointment:appointmentList){
             appointmentResponseDtoList.add(AppointmentResponseDto.builder()
                .userName(appointment.getUser().getName())
                .appontmentNo(appointment.getAppointmentNo())
                .appointmentDate(appointment.getAppointment_Date())
                .doseNo(appointment.getDoseNo())
                .doctorName(appointment.getDoctor().getName())
                .build());
        }
        return appointmentResponseDtoList;
    }
}
