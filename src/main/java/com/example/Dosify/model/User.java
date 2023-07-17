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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "user")
public class User {
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
//    @Pattern(regexp="\\d{10}",message="Mobile number must be 10 digits")
//    @Size(max=10,message="Mobile number must be 10 digits")
    String mobNo;
    @Column(name="is_dose1_taken")
    boolean isDose1Taken;
    @Column(name="is_dose2_taken")
    boolean isDose2Taken;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Appointment>appointments=new ArrayList<>();
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Dose_1 dose_1;
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Dose_2 dose_2;
    @OneToOne(mappedBy ="user",cascade = CascadeType.ALL)
    Certificate certificate;
}
