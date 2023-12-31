package com.example.Dosify.model;

import com.example.Dosify.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "doctor")
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="name")
    String name;
    @Column(name="age")
    int age;
    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Column(name="email_id",unique = true,nullable = false)
    String emailId;
    @Column(name="mob_no",unique = true,nullable = false)
    String mobNo;
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    List<Appointment> appointments=new ArrayList<>();
   @ManyToOne
    @JoinColumn
    VaccinationCenter vaccinationCenter;
}
