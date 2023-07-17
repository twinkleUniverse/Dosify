package com.example.Dosify.model;

import com.example.Dosify.Enum.VaccinationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "dose_1")
public class Dose_1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "dose_id")
    String doseId;
    @Column(name="vaccine_type")
    @Enumerated(EnumType.STRING)
    VaccinationType vaccinationType;
    @Column(name="vaccination_date")
    @CreationTimestamp
    Date VaccinationDate;
    @OneToOne
    @JoinColumn
    User user;
}
