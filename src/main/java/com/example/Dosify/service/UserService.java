package com.example.Dosify.service;

import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.exception.UserNotFoundException;
import com.example.Dosify.model.User;


import java.util.List;


public interface UserService {
   public UserResponseDto addUser(UserRequestDto userRequestDto);

    public UserResponseDto getByEmailId(String emailId);

    public UserResponseDto upadateUserNameUsingMob(String mobNo,String name) throws UserNotFoundException;

    public List<UserResponseDto> getFullVaccinatedFemales();
    public List<UserResponseDto>getMalesWithoutVaccine();

   public List<UserResponseDto> getUsersWithoutSingleDose();
   public List<UserResponseDto>AllUsersWithDose1();

   public List<UserResponseDto> fullyVaccinatedUsers();

    List<UserResponseDto> UserOfAgeGether(int age);
}
