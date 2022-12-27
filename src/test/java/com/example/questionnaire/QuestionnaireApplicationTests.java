package com.example.questionnaire;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.Questions;
import com.example.questionnaire.respository.QuestionnaireDao;
import com.example.questionnaire.respository.QuestionsDao;

@SpringBootTest
class QuestionnaireApplicationTests {
	
	@Autowired
	private QuestionnaireDao questionnaireDao;
	
	@Autowired
	private QuestionsDao questionsDao;

	@Test
	public void findByCaption() {
		Optional<Questionnaire> questionnaire = questionnaireDao.findByCaption("測試01");
		System.out.println("問卷名稱 : " + questionnaire);
	}

	@Test
	public void findByCaption2() {
		List<Questions> questionsList = questionsDao.findByCaption("測試01");
		for(Questions item : questionsList) {
		System.out.printf("問卷名稱 :%s 問題 :%s 選項 :%s\n", item.getCaption() ,item.getQuestions() ,item.getOptions());
		}
	}
}
