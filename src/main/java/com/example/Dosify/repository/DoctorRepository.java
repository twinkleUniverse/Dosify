package com.example.Dosify.repository;

import com.example.Dosify.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    public Doctor findByEmailId(String emailId);
    @Query(value = "select * from doctor s where s.age > :age",nativeQuery = true)
    public List<Doctor>doctorListWithAge(int age);
}
