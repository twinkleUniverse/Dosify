package com.example.Dosify.service.impl;

import com.example.Dosify.dto.ResponseDTO.CertificateResponseDto;
import com.example.Dosify.exception.UserNotFoundException;
import com.example.Dosify.model.Certificate;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.CenterRepository;
import com.example.Dosify.repository.CertificateRepository;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.CertificateService;
import com.example.Dosify.transformer.CertificateTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CertificateRepository certificateRepository;
    public CertificateResponseDto create(int userId) throws UserNotFoundException {
        Optional<User>optionalUser=userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw  new UserNotFoundException("Invalid User Id");
        }
        User user=optionalUser.get();
        Certificate certificate= Certificate.builder()
                                 .user(user)
                                 .build();
        Certificate savedCertificate=certificateRepository.save(certificate);
        user.setCertificate(savedCertificate);
        return CertificateTransformer.certificateToResponseDto(certificate);
    }

    public CertificateResponseDto get(int userId)  throws UserNotFoundException {
        Optional<User>optionalUser=userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw  new UserNotFoundException("Invalid User Id");
        }
        User user=optionalUser.get();
        return CertificateTransformer.certificateToResponseDto(user.getCertificate());
    }

}
