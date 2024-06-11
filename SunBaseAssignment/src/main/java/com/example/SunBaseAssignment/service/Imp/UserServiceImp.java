package com.example.SunBaseAssignment.service.Imp;

import com.example.SunBaseAssignment.customExceptions.InvalidSearchException;
import com.example.SunBaseAssignment.customExceptions.UserNotFoundException;
import com.example.SunBaseAssignment.dto.request.UserRequestDto;
import com.example.SunBaseAssignment.dto.responce.UserResponceDto;
import com.example.SunBaseAssignment.models.User;
import com.example.SunBaseAssignment.repository.UserRepository;
import com.example.SunBaseAssignment.service.UserService;
import com.example.SunBaseAssignment.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponceDto addUser(UserRequestDto userRequestDto) {
        User user = UserTransformer.userFromUserRequestDto(userRequestDto);

        //Just You have to save this User into the database and return that you created the User..
        User savedUser = userRepository.save(user);

        UserResponceDto userResponceDto = UserTransformer.userResponceDtoFromUser(savedUser);
        userResponceDto.setMessage("User Has Been Added to teh Db Successfully!!");

        return userResponceDto;
    }

    @Override
    public UserResponceDto updateUser(int id, UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            //Instead of Throwing an error you can Save it as it was writtern in that doc..
            UserResponceDto dto = addUser(userRequestDto);
            dto.setMessage("We Didn't Found Anything Related to this Id so. We have created Your Account");
            return dto;
        }

        User user = optionalUser.get();
        user = UserTransformer.userFromUserDto(user, userRequestDto);

        User savedUser = userRepository.save(user);

        UserResponceDto responceDto = UserTransformer.userResponceDtoFromUser(savedUser);
        responceDto.setMessage("User With name:" + user.getFirstName() + " has been Updated");
        return responceDto;
    }

    @Override
    public UserResponceDto deleteUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new UserNotFoundException("User with id " + id + " is Not Found");
        userRepository.deleteById(id); //this wll delete the User
        User user = optionalUser.get();
        UserResponceDto responceDto = UserTransformer.userResponceDtoFromUser(user);
        responceDto.setMessage("User with id:" + user.getId() + " has been deleted Successfully!!");
        return responceDto;
    }

    @Override
    public List<UserResponceDto> getUsersBy(String search, String value) {
        List<User> userList;
        switch (search) {
            case "city": {
                userList = userRepository.findByCity(value);
                break;
            }
            case "phone": {
                userList = userRepository.findByPhone(value);
                break;
            }
            case "first": {
                userList = userRepository.findByFirstName(value);
                break;
            }
            case "email": {
                userList = userRepository.findByEmail(value);
                break;
            }
            default: {
               userList=userRepository.findAll();
            }
        }

        //else I'll have the value..

        //let's Convert the  Every User to UserResponce dto using our Transformer Function and I've actually Used
        List<UserResponceDto> userResponceDtos = userList.stream()
                .map(ele -> UserTransformer.userResponceDtoFromUser(ele))
                .collect(Collectors.toList());



        return userResponceDtos;
    }

    @Override
    public UserResponceDto getUser(int id) {
        Optional<User>optionalUser=userRepository.findById(id);
        if(optionalUser.isEmpty())throw new UserNotFoundException("Unable Find User with userId:"+id);

        User user=optionalUser.get();
        UserResponceDto dto=UserTransformer.userResponceDtoFromUser(user);
        dto.setMessage("User is Found");
        return dto;
    }
}
