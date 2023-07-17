package com.example.Dosify.controller;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @PostMapping("/add_doctor")
    public ResponseEntity addDoctor(@RequestBody() DoctorRequestDto doctorRequestDto){
       try {
           DoctorResponseDto doctorResponseDto=doctorService.add_Doctor(doctorRequestDto);
           return  new ResponseEntity<>(doctorResponseDto, HttpStatus.CREATED);
       }catch (CenterNotPresentException e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }
     @GetMapping("/get_Doctors_morethan_num_appointments/{number}")
    public List<DoctorResponseDto> doctor_MoreThan_num_Appointments(@PathVariable("number") int nmb){
        return doctorService.doctorWithAppointment(nmb);
    }
    @GetMapping("/get_DoctorOf_specific_ageGroup/{age}")
    public List<DoctorResponseDto>doctorOfSpecificAgeGroup(@PathVariable("age")int age){
        return doctorService.doctorOfAgeGroup(age);
    }
    @PutMapping("/Update_name_usingEmailId")
    public DoctorResponseDto udateDetailsOfDoctor(@RequestParam("emailId")String emailId,@RequestParam("name")String name){
        return doctorService.updateDetails(emailId,name);
    }

    @PutMapping("/Update_age_usingEmailId")
    public DoctorResponseDto udateDetailsOfDoctor(@RequestParam("emailId")String emailId,@RequestParam("age")int age){
        return doctorService.updateDetails(emailId,age);
    }
    @PutMapping("/Update_gender_usingEmailId")
    public DoctorResponseDto udateDetailsOfDoctor(@RequestParam("emailId")String emailId, @RequestParam("gender")Gender gender){
        return doctorService.updateDetails(emailId,gender);
    }
    @GetMapping("/get_ration")
    public double rationOfFemaleToMaleDoctors(){
        return doctorService.ration();
    }
}
