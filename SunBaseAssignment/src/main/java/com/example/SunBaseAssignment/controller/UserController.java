package com.example.SunBaseAssignment.controller;

import com.example.SunBaseAssignment.dto.request.UserRequestDto;
import com.example.SunBaseAssignment.dto.responce.ExceptionResponseDto;
import com.example.SunBaseAssignment.dto.responce.UserResponceDto;
import com.example.SunBaseAssignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity createUser(@RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponceDto responceDto = userService.addUser(userRequestDto);
            return new ResponseEntity(responceDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ExceptionResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestParam("id") int id, @RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponceDto userResponce = userService.updateUser(id, userRequestDto);

            return new ResponseEntity(userResponce, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ExceptionResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam("id") int userId) {
        try {
            UserResponceDto userResponceDto = userService.deleteUser(userId);
            return new ResponseEntity(userResponceDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ExceptionResponseDto(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find-by-search/{search}")
    public ResponseEntity findBySearch(@PathVariable("search") String search,@RequestParam("value") String value) {
        try {
            List<UserResponceDto> responceDto = userService.getUsersBy(search,value);
            return new ResponseEntity<>(responceDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new ExceptionResponseDto(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity findById(@RequestParam("id")int userId){
        try{
                UserResponceDto userResponceDto=userService.getUser(userId);
                return new ResponseEntity(userResponceDto,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity(new ExceptionResponseDto(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
}
