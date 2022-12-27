package com.example.questionnaire.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.User;

@Repository  //用於資料處理(CRUD)讓SpringBoot託管
public interface UserDao extends JpaRepository<User, String>{

}
