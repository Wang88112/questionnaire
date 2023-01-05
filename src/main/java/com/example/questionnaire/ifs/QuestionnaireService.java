package com.example.questionnaire.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.UserAnswer;
import com.example.questionnaire.entity.UserInfo;
import com.example.questionnaire.vo.QuestionnaireRes;
import com.example.questionnaire.vo.QuestionnaireResList;

public interface QuestionnaireService {

	//1-1創建問卷
	public Questionnaire createQuestionnaire(String caption, String content, LocalDate startDate, LocalDate endDate);

	//1-2更新問卷
	public QuestionnaireRes updateQuestionnaire(int serialNumber, String caption, String content, LocalDate startDate, LocalDate endDate);
	
	//1-3刪除問卷(需修改成布林值控制)
	public QuestionnaireRes deleteQuestionnaire(String caption);
	
	//2-1創建問題
	public QuestionnaireRes createQuestions(int serialNumber, int questionsId, String questions, boolean questionsType, String choose);

	//2-2更新問題
	public QuestionnaireRes updateQuestions(int questionsAiId, String questions, boolean questionsType, String choose);
	
	//2-3刪除問題(需修改成布林值控制)
	public QuestionnaireRes deleteQuestions(int questionsId);
	
    //3創建使用者資料
	public UserInfo createUser(String userName, String phone, String email, String age);
	
	//4搜尋(依照問卷名稱模糊搜尋)
	public QuestionnaireRes findByCaptionContaining(String caption);
    
	//5搜尋(依照開始時間即結束時間)
	public QuestionnaireRes findByStartDateAndEndDateBetween(LocalDate startDate, LocalDate endDate);
	
	//儲存回答者問題
	public QuestionnaireRes createAns(int userId, int serialNumber, int questionsId, String choose);
	
	//儲存回答者問題(List)
	public QuestionnaireRes createAns1(List<UserAnswer> ansList);
	
	//一次處存
	public QuestionnaireResList createUserInfoAndAns(String userName, String phone, String email, String age, 
			 List<UserAnswer> ansList);
		
	//6統計選項
	public QuestionnaireRes statisticalData(int serialNumber);
}
