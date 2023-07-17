package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.CenterType;
import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.exception.CenterNotPresentException;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.CenterRepository;
import com.example.Dosify.service.CenterService;
import com.example.Dosify.transformer.CenterTransformer;
import com.example.Dosify.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    CenterRepository centerRepository;

    @Override
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {
        //requestDto to center
        VaccinationCenter center= CenterTransformer.centerRequstDtoToCenter(centerRequestDto);
        //save center
        VaccinationCenter savedCenter=centerRepository.save(center);
        //center to responseDto
        return CenterTransformer.centerToCenterResponseDto(savedCenter);
    }
    public List<DoctorResponseDto> listOfDoctors(int centerId) throws CenterNotPresentException{
        Optional<VaccinationCenter>optionalCenter=centerRepository.findById(centerId);
        if(!optionalCenter.isPresent()){
            throw new CenterNotPresentException("Vaccination center does not exist!!");
        }
        VaccinationCenter center=optionalCenter.get();
        List<Doctor>doctorsList=center.getDoctors();
        return DoctorTransformer.doctorResponseDto(doctorsList);
    }
    public List<DoctorResponseDto>listOfMaleDoctors(int centerId) throws CenterNotPresentException {
        Optional<VaccinationCenter>optionalCenter=centerRepository.findById(centerId);
        List<Doctor>doclist=new ArrayList<>();
        if(!optionalCenter.isPresent()){
            throw new CenterNotPresentException("Vaccination center does not exist!!");
        }
        VaccinationCenter center=optionalCenter.get();
        List<Doctor>doctorsList=center.getDoctors();
       for(Doctor doc:doctorsList){
           if(doc.getGender().equals(Gender.MALE)){
               doclist.add(doc);
           }
       }
       return DoctorTransformer.doctorResponseDto(doclist);
    }
    public List<DoctorResponseDto>listOfFemaleDoctors(int centerId) throws CenterNotPresentException {
        Optional<VaccinationCenter>optionalCenter=centerRepository.findById(centerId);
        List<Doctor>doclist=new ArrayList<>();
        if(!optionalCenter.isPresent()){
            throw new CenterNotPresentException("Vaccination center does not exist!!");
        }
        VaccinationCenter center=optionalCenter.get();
        List<Doctor>doctorsList=center.getDoctors();
        for(Doctor doc:doctorsList){
            if(doc.getGender().equals(Gender.FEMALE)){
                doclist.add(doc);
            }
        }
        return DoctorTransformer.doctorResponseDto(doclist);
    }

    public List<DoctorResponseDto> genderDoctorsOfAge(int centerId, int age, Gender gender) throws CenterNotPresentException {
        Optional<VaccinationCenter>optionalCenter=centerRepository.findById(centerId);
        List<Doctor>doclist=new ArrayList<>();
        if(!optionalCenter.isPresent()){
            throw new CenterNotPresentException("Vaccination center does not exist!!");
        }
        VaccinationCenter center=optionalCenter.get();
        List<Doctor>doctorsList=center.getDoctors();
        for(Doctor doc:doctorsList){
            if(doc.getAge()==age&&doc.getGender().equals(gender)){
                doclist.add(doc);
            }
        }
        return DoctorTransformer.doctorResponseDto(doclist);
    }
    public List<CenterResponseDto>getCenters(CenterType centerType){
        List<VaccinationCenter>vaccinationCenterList=centerRepository.findAllByCenterType(centerType);
        return CenterTransformer.centerToCenterResponseDto(vaccinationCenterList);
    }
}
