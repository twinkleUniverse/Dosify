package com.example.Dosify.model;

import com.example.Dosify.Enum.CenterType;
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
@Builder
@Table(name = "vaccination_center")
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="name")
    String name;
    @Column(name="location")
    String Location;
    @Column(name="center_type")
    @Enumerated(EnumType.STRING)
    CenterType centerType;
    @OneToMany(mappedBy = "vaccinationCenter",cascade = CascadeType.ALL)
    List<Doctor>doctors=new ArrayList<>();
}
