package com.example.Dosify.transformer;

import com.example.Dosify.dto.RequestDTO.CenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.CenterResponseDto;
import com.example.Dosify.model.VaccinationCenter;

import java.util.ArrayList;
import java.util.List;

public class CenterTransformer {
    public static VaccinationCenter centerRequstDtoToCenter(CenterRequestDto centerRequestDto){
        return VaccinationCenter.builder()
                .name(centerRequestDto.getName())
                .centerType(centerRequestDto.getCenterType())
                .Location(centerRequestDto.getLocation())
                .build();
    }
    public static CenterResponseDto centerToCenterResponseDto(VaccinationCenter center){
        return CenterResponseDto.builder()
                .name(center.getName())
                .location(center.getLocation())
                .centerType(center.getCenterType())
                .build();
    }
    public static List<CenterResponseDto> centerToCenterResponseDto(List<VaccinationCenter> vacCenter){
       List<CenterResponseDto>centerResponseDtosList=new ArrayList<>();
       for(VaccinationCenter center:vacCenter){
           centerResponseDtosList.add(centerToCenterResponseDto(center));
       }
       return centerResponseDtosList;
    }

}
