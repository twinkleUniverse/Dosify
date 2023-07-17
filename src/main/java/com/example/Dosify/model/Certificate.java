package com.example.Dosify.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Certificate")
@Builder
public class Certificate {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    @OneToOne
    @JoinColumn
    User user;//name,age,gender,emailId,mobNo,Dose1_taken,Dose2_taken,appointment
    //appointment -> appointment.date,appointment.doseNo,appointment.doctor
    //doctor->doctor.name,doctor.centername;

}
