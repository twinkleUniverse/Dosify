package com.example.Dosify.service.impl;

import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.exception.UserNotFoundException;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.UserService;

import com.example.Dosify.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    public UserResponseDto addUser(UserRequestDto userRequestDto){
        //convert requestdto to entity
        User user= UserTransformer.userRequestTouser(userRequestDto);
        //save entity
        User savedUser =userRepository.save(user);
        //Convert entity to responseDto
        return UserTransformer.userToUserResponsDto(savedUser);
    }

    public UserResponseDto getByEmailId(String emailId){
        User user=userRepository.findByEmailId(emailId);
        return UserTransformer.userToUserResponsDto(user);
    }

    @Override
    public UserResponseDto upadateUserNameUsingMob(String mobNo,String name) throws UserNotFoundException {
        User user=userRepository.findByMobNo(mobNo);
        if(user==null){
            throw new UserNotFoundException("User does not Exist!");
        }
        user.setName(name);
        User SavedUser=userRepository.save(user);
        UserResponseDto userResponseDto= UserTransformer.userToUserResponsDto(SavedUser);
        userResponseDto.setMessage("Name has changed successfully");
        return userResponseDto;
    }

    public List<UserResponseDto> getUsersWithoutSingleDose(){
        List<User>usersList=userRepository.AllUsersWithoutVaccination();
        return UserTransformer.userToUserResponsDto(usersList,"Please! Take your FIRST DOSE");
    }

    @Override
    public List<UserResponseDto> AllUsersWithDose1() {
        List<User>userList=userRepository.UserTakenDose1NotDose2();
        return UserTransformer.userToUserResponsDto(userList,"Please! Book appointment for DOSE 2");
    }

    @Override
    public List<UserResponseDto> fullyVaccinatedUsers() {
        List<User>userList=userRepository.usersWithBothDose();
        return UserTransformer.userToUserResponsDto(userList,"Congratulate! You are fully Vaccinated");
    }

    public List<UserResponseDto> getFullVaccinatedFemales(){
        List<User> femalesList=userRepository.getFullVaccinatedfemales();
        return UserTransformer.userToUserResponsDto(femalesList,"Congratulate! You are fully Vaccinated");
    }

    public List<UserResponseDto> getMalesWithoutVaccine(){
        List<User> malesList=userRepository.getMalesWithoutSingleVaccine();
        return UserTransformer.userToUserResponsDto(malesList,"Please! Take your FIRST DOSE");
    }
    public List<UserResponseDto> UserOfAgeGether(int age){
        List<User> h=userRepository.User_with_AGE_greaterThan_Given_AGE(age);
        return  UserTransformer.userToUserResponsDto(h,"Users of given age");
    }
}
