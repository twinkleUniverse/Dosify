package com.example.Dosify.controller;

import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.exception.UserNotFoundException;
import com.example.Dosify.model.User;
import com.example.Dosify.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/add_user")
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto Newuser= userService.addUser(userRequestDto);
        return Newuser;
    }
    @GetMapping("/getbyEId/{emailId}")
    public UserResponseDto getByEmailId(@PathVariable("emailId")String emailId){
       return  userService.getByEmailId(emailId);
    }
    //Update the name of the user using mobile Number
    @PutMapping("/change_user_name")
    public ResponseEntity updateUserNameUsingMob(@RequestParam("mobNo")String mobNo, @RequestParam("name")String name) throws UserNotFoundException{
        try{
           UserResponseDto userResponseDto= userService.upadateUserNameUsingMob(mobNo,name);
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        }catch (UserNotFoundException u){
            return new ResponseEntity<>(u.getMessage(),HttpStatus.NOT_FOUND);
        }


    }
    //User without single dose
    @GetMapping("/get_users_without_single_vaccine")
    public List<UserResponseDto>get_Users_without_vaccine(){
        return userService.getUsersWithoutSingleDose();
    }
    @GetMapping("/get_fullyvaccinated_users")
    public List<UserResponseDto>get_fullyVaccinated_users(){
        return userService.fullyVaccinatedUsers();
    }

    //Get full Vaccinated females
    @GetMapping("/get_fullVaccinated_females")
    public List<UserResponseDto> getFull_Vaccinated_females(){
        List<UserResponseDto>vaccinatedFemalesList=userService.getFullVaccinatedFemales();
        return vaccinatedFemalesList;
    }

    //all males without single dose
    @GetMapping("/get_males_without_single_vaccine")
    public List<UserResponseDto>getMalesWithoutSingleDose(){
        return userService.getMalesWithoutVaccine();
    }
    @GetMapping("/get_users_withDose1_notDose2")
    public List<UserResponseDto> usersWithDose1notDose2(){
        return userService.AllUsersWithDose1();
    }
    @GetMapping("/get_age/{age}")
    public List<UserResponseDto>user_with_greaterAge_Than_GivenAge(@PathVariable("age")int age){
        List<UserResponseDto>list=userService.UserOfAgeGether(age);
        return list;
    }
}
