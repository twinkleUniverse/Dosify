package com.example.Dosify.controller;

import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.exception.DoctorNotFoundException;
import com.example.Dosify.exception.NotEligibleForDoseException;
import com.example.Dosify.exception.UserNotFoundException;
import com.example.Dosify.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book_appointment")
    public ResponseEntity bookAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) throws UserNotFoundException, DoctorNotFoundException, NotEligibleForDoseException {
       try{
           AppointmentResponseDto appointmentResponseDto=appointmentService.book(appointmentRequestDto);
           return new ResponseEntity<>(appointmentResponseDto,HttpStatus.OK);
       }catch(UserNotFoundException u){
           return new ResponseEntity(u.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (DoctorNotFoundException d) {
           return new ResponseEntity(d.getMessage(), HttpStatus.BAD_REQUEST);
       }catch (NotEligibleForDoseException n){
           return new ResponseEntity<>(n.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/get_appointment_details/{userId}")
    public ResponseEntity getAppointmentDetails(@PathVariable("userId")int user_Id) throws UserNotFoundException{
        try{
            List<AppointmentResponseDto> appointmentResponseDto=appointmentService.appointmentDetails(user_Id);
            return  new ResponseEntity<>(appointmentResponseDto,HttpStatus.OK);
        }catch (UserNotFoundException u){
            return new ResponseEntity(u,HttpStatus.BAD_REQUEST);
        }
    }
}
