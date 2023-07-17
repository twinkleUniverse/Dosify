package com.example.Dosify.dto.ResponseDTO;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.Enum.Gender;
import com.example.Dosify.Enum.VaccinationStatus;
import com.example.Dosify.Enum.VaccinationType;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CertificateResponseDto {
    String UserName;
    int age;
    Gender gender;
    VaccinationStatus vaccinStatus;
    VaccinationType vaccinationType;
    DoseNo doseNo;

    Date dateOfDose;
    String doctorName;
    String centerName;
}
