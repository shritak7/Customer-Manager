package com.example.SunBaseAssignment.service;

import com.example.SunBaseAssignment.dto.request.UserRequestDto;
import com.example.SunBaseAssignment.dto.responce.UserResponceDto;

import java.util.List;

public interface UserService {
    UserResponceDto addUser(UserRequestDto userRequestDto);
    UserResponceDto updateUser(int id,UserRequestDto userRequestDto);

    UserResponceDto deleteUser(int id);

    List<UserResponceDto>getUsersBy(String search,String value);

    UserResponceDto getUser(int id);
}
