package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.model.Appointment;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.CenterRepository;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.service.DoctorService;
import com.example.Dosify.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    CenterRepository centerRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public DoctorResponseDto add_Doctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException {
        Optional<VaccinationCenter>optionalCenter=centerRepository.findById(doctorRequestDto.getCenterId());
        if(!optionalCenter.isPresent()){
            throw new CenterNotPresentException("Invalid Center Id!!");
        }
        VaccinationCenter center=optionalCenter.get();
        //doctorRequestDto to doctor
        Doctor doctor=DoctorTransformer.doctorRequestDtoToDoctor(doctorRequestDto);
        //set center to doctor as doctor is child
        doctor.setVaccinationCenter(center);
        //add doctor to the list of doctor in center
        center.getDoctors().add(doctor);
        //this will save doctor+center
        centerRepository.save(center);
        return DoctorTransformer.doctorResponseDto(doctor);
    }
     public List<DoctorResponseDto> doctorWithAppointment(int nmb){
        List<Doctor>doctorList=doctorRepository.findAll();
        List<DoctorResponseDto>doctorResponseDtoList=new ArrayList<>();
        for(Doctor doc:doctorList){
            List<Appointment>appointmentList=doc.getAppointments();
            if(appointmentList.size()>nmb){
               doctorResponseDtoList.add(DoctorTransformer.doctorResponseDto(doc));
            }
        }
        return doctorResponseDtoList;
     }

     public List<DoctorResponseDto>doctorOfAgeGroup(int age){
        List<Doctor>doctorList=doctorRepository.doctorListWithAge(age);
       return DoctorTransformer.doctorResponseDto(doctorList);
    }

    public DoctorResponseDto updateDetails(String emailId, String name){
        Doctor doctor=doctorRepository.findByEmailId(emailId);
        doctor.setName(name);
        doctorRepository.save(doctor);
        return DoctorTransformer.doctorResponseDto(doctor);
    }
    public DoctorResponseDto updateDetails(String emailId, int age){
        Doctor doctor=doctorRepository.findByEmailId(emailId);
        doctor.setAge(age);
        doctorRepository.save(doctor);
        return DoctorTransformer.doctorResponseDto(doctor);
    }
    public DoctorResponseDto updateDetails(String emailId, Gender gender){
        Doctor doctor=doctorRepository.findByEmailId(emailId);
        doctor.setGender(gender);
        doctorRepository.save(doctor);
        return DoctorTransformer.doctorResponseDto(doctor);
    }

    public Double ration(){
        List<Doctor>doctorList=doctorRepository.findAll();
        double female_doc_Count=0;
        double male_doc_Count=0;
        for(Doctor doc:doctorList){
            if(doc.getGender().equals(Gender.FEMALE))
                female_doc_Count++;
            else if(doc.getGender().equals(Gender.MALE))
                male_doc_Count++;
        }
        double ration=(female_doc_Count/male_doc_Count);

        return  ration;
    }

}
