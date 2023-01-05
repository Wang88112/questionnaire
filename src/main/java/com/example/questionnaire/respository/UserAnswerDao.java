package com.example.questionnaire.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.UserAnswer;

@Repository  //用於資料處理(CRUD)讓SpringBoot託管
public interface UserAnswerDao extends JpaRepository<UserAnswer, Integer>{
 
	public List<UserAnswer> findByUserId(int userId);
	
//	public Optional<UserAnswer> findBySerialNumberAndQuestionsId(int serialNumber, int questionsId);
	
	public List<UserAnswer> findBySerialNumberAndQuestionsId(int serialNumber, int questionsId);
}
