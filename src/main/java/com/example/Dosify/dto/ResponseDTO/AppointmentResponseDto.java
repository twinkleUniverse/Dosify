package com.example.Dosify.dto.ResponseDTO;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.Enum.VaccinationType;
import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.model.Appointment;
import com.example.Dosify.transformer.AppointmentTransformer;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponseDto {
    String userName;
    String appontmentNo;
    Date appointmentDate;
    DoseNo doseNo;
    CenterResponseDto centerResponseDto;
    String doctorName;
    VaccinationType vaccinationType;


}
