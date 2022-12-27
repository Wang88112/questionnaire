package com.example.questionnaire.ifs;

import java.time.LocalDate;
import java.util.Date;

import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.Questions;
import com.example.questionnaire.entity.User;
import com.example.questionnaire.vo.QuestionnaireRes;

public interface QuestionnaireService {

	//1-1創建問卷
	public Questionnaire createQuestuonnaire(String caption, String content, LocalDate startDate, LocalDate endDate);

	//1-2更新問卷
	public QuestionnaireRes updateQuestuonnaire(String caption, String newCaption, String content, LocalDate startDate, LocalDate endDate);
	
	//1-3刪除問卷
	public QuestionnaireRes deleteQuestionnaire(String caption);
	
	//2-1創建問題
	public QuestionnaireRes createQuestions(String caption, String questions, String potions);

	//2-2更新問題
	public QuestionnaireRes updateQuestions(String caption, String questions, String newQuestions, String potions);
	
	//2-3刪除問題
	public QuestionnaireRes deleteQuestions(String caption, String questions);
	
    //3創建使用者資料
	public User createUser(String userName);
    
}
