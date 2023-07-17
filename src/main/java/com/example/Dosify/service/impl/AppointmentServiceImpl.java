package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.exception.DoctorNotFoundException;
import com.example.Dosify.exception.NotEligibleForDoseException;
import com.example.Dosify.exception.UserNotFoundException;
import com.example.Dosify.model.*;
import com.example.Dosify.repository.CenterRepository;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.AppointmentService;
import com.example.Dosify.service.CertificateService;
import com.example.Dosify.service.Dose1Service;
import com.example.Dosify.service.Dose2Service;
import com.example.Dosify.transformer.AppointmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    Dose1Service dose1Service;
    @Autowired
    Dose2Service dose2Service;
    @Autowired
    CertificateService certificateService;

    @Autowired
    private JavaMailSender emailSender;


    @Override
    public AppointmentResponseDto book(AppointmentRequestDto appointmentRequestDto) throws NotEligibleForDoseException,UserNotFoundException,DoctorNotFoundException{
        Optional<User>optionalUser=userRepository.findById(appointmentRequestDto.getUserId());
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User does not exist!!");
        }
        Optional<Doctor>optionalDoctor=doctorRepository.findById(appointmentRequestDto.getDoctorId());
        if(!optionalDoctor.isPresent()){
            throw new DoctorNotFoundException("Doctor does not exist!!");
        }
        User user=optionalUser.get();
        Doctor doctor=optionalDoctor.get();
        if(appointmentRequestDto.getDoseNo()== DoseNo.DOSE_1){
            Dose_1 dose_1=dose1Service.createDose1(appointmentRequestDto.getVaccinationType(),user);
            user.setDose1Taken(true);
            user.setDose_1(dose_1);
        }else {
            if(!user.isDose1Taken()){
                throw new NotEligibleForDoseException("Sorry! You are not yet eligible for Dose 2");
            }

            Dose_2 dose_2=dose2Service.createDose2(appointmentRequestDto.getVaccinationType(),user);
            user.setDose2Taken(true);
            user.setDose_2(dose_2);
        }
        Appointment appointment= AppointmentTransformer.appointmentRequestDtoToAppointment(appointmentRequestDto,user,doctor);
        user.getAppointments().add(appointment);
        User savedUser=userRepository.save(user);//save dose1 and dose2
        Appointment savedAppointment=savedUser.getAppointments().get(savedUser.getAppointments().size()-1);
        doctor.getAppointments().add(savedAppointment);
        Doctor savedDoctor=doctorRepository.save(doctor);//doctor saved with appointment

        certificateService.create(savedUser.getId());

        String text="CONGRATS..!!\n"+savedUser.getName()+" Your "+appointmentRequestDto.getDoseNo()+" has been booked\n"+"Doctor Name: "
                +savedDoctor.getName();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dosifyapplication@gmail.com");
        message.setTo(savedUser.getEmailId());
        message.setSubject("Appointment Booked !!");
        message.setText(text);
        emailSender.send(message);
        return AppointmentTransformer.appointToAppointmentResponseDto(savedAppointment,savedUser,savedDoctor,appointmentRequestDto.getVaccinationType());

    }

    public List<AppointmentResponseDto> appointmentDetails(int userId) throws UserNotFoundException{
        Optional<User>optionalUser=userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("Invalid User Id");
        }
        User user=optionalUser.get();
        List<Appointment>appointmentList=user.getAppointments();

        return AppointmentTransformer.appointToAppointmentResponseDto(appointmentList);
    }
}
