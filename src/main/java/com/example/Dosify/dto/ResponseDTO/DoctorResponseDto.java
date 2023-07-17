package com.example.Dosify.dto.ResponseDTO;

import com.example.Dosify.Enum.Gender;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DoctorResponseDto {
    String name;
    String mobNo;
    String emailId;
    CenterResponseDto centerResponseDto;
}
