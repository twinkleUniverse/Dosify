package com.example.Dosify.transformer;

import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserTransformer {

    //User RequestDto to User entity
    public static User userRequestTouser(UserRequestDto userRequestDto){
        return User.builder()
                .name(userRequestDto.getName())
                .age(userRequestDto.getAge())
                .gender(userRequestDto.getGender())
                .emailId(userRequestDto.getEmailId())
                .mobNo(userRequestDto.getMobNo())
                .build();
    }

    //User to User ResponseDto
    public static UserResponseDto userToUserResponsDto(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .mobNo(user.getMobNo())
                .emailId(user.getEmailId())
                .message("Congrats! You have registered for Dosify")
                .build();
    }

    public static List<UserResponseDto> userToUserResponsDto(List<User>userList,String msg){
        List<UserResponseDto>userResponseDtoList=new ArrayList<>();
        for(User user:userList) {
           UserResponseDto userResponseDto=UserResponseDto.builder()
                    .name(user.getName())
                    .mobNo(user.getMobNo())
                    .emailId(user.getEmailId())
                    .message(msg)
                    .build();
           userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }

}
