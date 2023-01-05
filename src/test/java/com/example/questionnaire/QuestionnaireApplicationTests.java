package com.example.questionnaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.Questions;
import com.example.questionnaire.respository.QuestionnaireDao;
import com.example.questionnaire.respository.QuestionsDao;
import com.example.questionnaire.vo.QuestionnaireRes;

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

//	@Test
//	public void findByCaption2() {
//		List<Questions> questionsList = questionsDao.findByCaption("測試01");
//		for(Questions item : questionsList) {
//		System.out.printf("問卷名稱 :%s 問題 :%s 選項 :%s\n", item.getQuestionsId() ,item.getQuestions() ,item.getChoose());
//		}
//	}
	
	@Test
	public void findByCaptionContaining() {
		List<Questionnaire> questionnaireList = questionnaireDao.findByCaptionContaining("1");
		for(Questionnaire item : questionnaireList) {
			System.out.printf("問卷名稱 :%s 內容 :%s 開始日期 :%s 結束日期 :%s\n", 
					item.getCaption() ,item.getContent() ,item.getStartDate(), item.getEndDate());
			}
		}
	
	@Test
	public void findByStartDateAndEndDateBetween() {
		String str1 = "2022-12-29";
		String str2 = "2023-03-01";
		LocalDate date1 =  LocalDate.parse(str1);
		LocalDate date2 =  LocalDate.parse(str2);
		List<Questionnaire> questionnaireList = questionnaireDao.findByStartDateBetween(date1, date2);
		List<Questionnaire> questionnaireList2 = questionnaireDao.findByEndDateBetween(date1, date2);
		System.out.println(questionnaireList.size());
		System.out.println(questionnaireList2.size());
	}
	
//	@Test
//	public void findByCaptionAndQuestions() {
//		List<Questions> findCaptionAndQuestionsList = questionsDao.findByCaptionAndQuestions("", null)
//	}
	
//	@Test
//	public void findBySerialNumber() {
//		List<Integer> Id = new ArrayList<>();
////		Set<Integer> Id = new HashSet<>();
//		List<Questionnaire> questionnaireList = questionnaireDao.findAllBySerialNumberIn(Id);
//		for(Questionnaire item : questionnaireList) {
//			System.out.printf("問卷名稱 :%s 內容 :%s 開始日期 :%s 結束日期 :%s\n", 
//					item.getCaption() ,item.getContent() ,item.getStartDate(), item.getEndDate());
//			}
//	}

}
