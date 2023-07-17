package com.example.Dosify.repository;


import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
import com.example.Dosify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //findByAge();
    public User findByEmailId(String emailId);

    public User findByMobNo(String mobNo);
    @Query(value = "select * from user u where u.gender='FEMALE' and u.is_dose1_taken=true and u.is_dose2_taken=true",nativeQuery = true)
    public List<User> getFullVaccinatedfemales();

    @Query(value="select * from user u where u.gender='MALE' and u.is_dose1_taken=false and u.is_dose2_taken=false",nativeQuery = true)
    public List<User>getMalesWithoutSingleVaccine();

    @Query(value = "select * from user u where u.is_dose1_taken=false and u.is_dose2_taken=false",nativeQuery = true)
    public List<User>AllUsersWithoutVaccination();

    @Query(value = "select * from user u where u.is_dose1_taken=true and u.is_dose2_taken=false",nativeQuery = true)
    public List<User>UserTakenDose1NotDose2();

    @Query(value = "select * from user u where u.is_dose1_taken=true and u.is_dose2_taken=true",nativeQuery = true)
    public List<User>usersWithBothDose();
    @Query(value = "select * from user s where s.age > :age",nativeQuery = true)
    public List<User>User_with_AGE_greaterThan_Given_AGE(int age);

}
