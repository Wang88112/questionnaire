package com.example.questionnaire.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.Questions;

@Repository  //用於資料處理(CRUD)讓SpringBoot託管
public interface QuestionsDao extends JpaRepository<Questions, Integer>{
	
	public Optional<Questions> findByQuestions(String questions);
	
	public Optional<Questions> findByQuestionsId(int questionsId);
	
	public List<Questions> findBySerialNumber(int serialNumber);
	
	public List<Questions> findAllByQuestionsIdIn(List<Integer> questionsIdList);
	
	public Questions findBySerialNumberAndQuestionsId(int serialNumber, int questionsId);
}
