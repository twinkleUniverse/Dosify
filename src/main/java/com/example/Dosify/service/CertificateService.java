package com.example.Dosify.service;

import com.example.Dosify.dto.ResponseDTO.CertificateResponseDto;
import com.example.Dosify.exception.UserNotFoundException;

public interface CertificateService {
    public CertificateResponseDto create(int userId)throws UserNotFoundException;
    public CertificateResponseDto get(int userId)  throws UserNotFoundException;



}
