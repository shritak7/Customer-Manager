package com.example.SunBaseAssignment.repository;

import com.example.SunBaseAssignment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    List<User>findByCity(String city);
    List<User> findByEmail(String email);
    List<User> findByPhone(String phone);

}
