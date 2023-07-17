package com.example.Dosify.controller;

import com.example.Dosify.dto.ResponseDTO.CertificateResponseDto;
import com.example.Dosify.exception.UserNotFoundException;
import com.example.Dosify.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    CertificateService certificateService;
    @PostMapping("/create_certificate")
    public ResponseEntity createCertificate(@RequestParam("userId")int userId) throws UserNotFoundException{
        try{
            CertificateResponseDto certificateResponseDto=certificateService.create(userId);
            return new ResponseEntity(certificateResponseDto, HttpStatus.CREATED);
        }catch (UserNotFoundException u){
            return new ResponseEntity<>(u.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_certificate")
    public ResponseEntity getCertificate(@RequestParam("userId")int userId) throws UserNotFoundException{
        try{
            CertificateResponseDto certificateResponseDto=certificateService.get(userId);
            return new ResponseEntity(certificateResponseDto, HttpStatus.FOUND);
        }catch (UserNotFoundException u){
            return new ResponseEntity<>(u.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
