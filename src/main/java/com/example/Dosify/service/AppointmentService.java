package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.exception.DoctorNotFoundException;
import com.example.Dosify.exception.NotEligibleForDoseException;
import com.example.Dosify.exception.UserNotFoundException;

import java.util.List;

public interface AppointmentService {
    public AppointmentResponseDto book(AppointmentRequestDto appointmentRequestDto) throws NotEligibleForDoseException,UserNotFoundException, DoctorNotFoundException;
    public List<AppointmentResponseDto> appointmentDetails(int UserId) throws UserNotFoundException;
}
