package com.example.Dosify.dto.RequestDTO;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.Enum.VaccinationType;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentRequestDto {
    DoseNo doseNo;
    int userId;
    int doctorId;
    VaccinationType vaccinationType;
}
