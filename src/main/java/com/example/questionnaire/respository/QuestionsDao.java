package com.example.questionnaire.respository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.Questions;
import com.example.questionnaire.entity.QuestionsId;

@Repository  //用於資料處理(CRUD)讓SpringBoot託管
public interface QuestionsDao extends JpaRepository<Questions, QuestionsId>{

	public List<Questions> findByCaption(String caption);
}
