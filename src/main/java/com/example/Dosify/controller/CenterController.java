package com.example.Dosify.controller;

import com.example.Dosify.Enum.CenterType;
import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccinationCenter")
public class CenterController {
    @Autowired
    CenterService centerService;
    @PostMapping("/add_vaccinationCenter")
    public CenterResponseDto addVaccineCenter(@RequestBody() CenterRequestDto centerRequestDto){
       return centerService.addCenter(centerRequestDto);
    }
    @GetMapping("/get_doctors_from_CenterId/{Id}")
    public ResponseEntity doctorsAtGivenCenter(@PathVariable("Id")int centerId)throws CenterNotPresentException {
        try{
            List<DoctorResponseDto>doctorResponseDtoList=centerService.listOfDoctors(centerId);
            return new ResponseEntity<>(doctorResponseDtoList, HttpStatus.OK);
        }catch (CenterNotPresentException c){
            return new ResponseEntity<>(c.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get_maleDoctors/{Id}")
    public ResponseEntity maleDoctorsAtGivenCenter(@PathVariable("Id")int Id) throws CenterNotPresentException{
        try{
            List<DoctorResponseDto>doctorResponseDtoList=centerService.listOfMaleDoctors(Id);
            return new ResponseEntity<>(doctorResponseDtoList, HttpStatus.OK);
        }catch (CenterNotPresentException c){
            return new ResponseEntity<>(c.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_femaleDoctors/{Id}")
    public ResponseEntity femaleDoctorsAtGivenCenter(@PathVariable("Id")int Id) throws CenterNotPresentException{
        try{
            List<DoctorResponseDto>doctorResponseDtoList=centerService.listOfFemaleDoctors(Id);
            return new ResponseEntity<>(doctorResponseDtoList, HttpStatus.OK);
        }catch (CenterNotPresentException c){
            return new ResponseEntity<>(c.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get_Doctors_ofAge_andGender")
    public ResponseEntity DoctorsOfAge(@RequestParam("id") int centerId,@RequestParam("age") int age,@RequestParam("gender") Gender gender) throws CenterNotPresentException{
        try{
            List<DoctorResponseDto>doctorResponseDtoList=centerService.genderDoctorsOfAge(centerId,age,gender);
            return new ResponseEntity<>(doctorResponseDtoList, HttpStatus.OK);
        }catch (CenterNotPresentException c){
            return new ResponseEntity<>(c.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get_by_centerType")
    public List<CenterResponseDto>allCentersOfType(@RequestParam("centerType")CenterType centerType){
        return centerService.getCenters(centerType);
    }
}
