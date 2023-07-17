package com.example.Dosify.transformer;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.Enum.VaccinationStatus;
import com.example.Dosify.dto.ResponseDTO.CertificateResponseDto;
import com.example.Dosify.model.Appointment;
import com.example.Dosify.model.Certificate;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.User;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


public class CertificateTransformer {
    public static CertificateResponseDto certificateToResponseDto(Certificate certificate){
       User user=certificate.getUser();
        Appointment appointment=user.getAppointments().get(user.getAppointments().size()-1);
       Doctor doctor=appointment.getDoctor();
        CertificateResponseDto certificateResponseDto=
                CertificateResponseDto.builder()
                        .UserName(user.getName())
                        .age(user.getAge())
                        .gender(user.getGender())
                        .doseNo(appointment.getDoseNo())
                        .doctorName(doctor.getName())
                        .centerName(doctor.getVaccinationCenter().getName())
                        .dateOfDose(appointment.getAppointment_Date())
                        .build();
        if(appointment.getDoseNo().equals(DoseNo.DOSE_1)){
            certificateResponseDto.setVaccinStatus(VaccinationStatus.PARTIALLY_VACCINATED);
            certificateResponseDto.setVaccinationType(user.getDose_1().getVaccinationType());
            return certificateResponseDto;
        }
            certificateResponseDto.setVaccinStatus(VaccinationStatus.FULLY_VACCINATED);
            certificateResponseDto.setVaccinationType(user.getDose_2().getVaccinationType());

        return certificateResponseDto;
    }
}
