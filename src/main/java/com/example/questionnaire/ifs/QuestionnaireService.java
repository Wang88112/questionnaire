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

	// !未連 1-2更新問卷
	public QuestionnaireRes updateQuestionnaire(int serialNumber, String caption, String content, LocalDate startDate, LocalDate endDate);
	
	//1-3刪除問卷(需修改成布林值控制(先不改))
	public QuestionnaireRes deleteQuestionnaire(int serialNumber);
	
	//2-1創建問題
	public QuestionnaireRes createQuestions(int serialNumber, int questionsId, String questions, boolean questionsType, String choose, boolean chooseType);

	//顯示問卷問題 0108
	public QuestionnaireResList getAllQuestions(int serialNumber);
	
	// !未連 2-2更新問題 
	public QuestionnaireRes updateQuestions(int questionsAiId, String questions, boolean questionsType, String choose, boolean chooseType);
	
	//2-3刪除問題(需修改成布林值控制(先不改)) 0108 OK
	public QuestionnaireRes deleteQuestions(int questionsId);
	

	//4搜尋(依照問卷名稱、開始以及結束時間模糊搜尋)
	public QuestionnaireRes findByCaptionContainingAndStartDateAndEndDate(String caption, LocalDate startDate, LocalDate endDate);
    
	//拿全部資料 0108 OK
	public QuestionnaireRes getAllCaptions();
	
	//一次處存
	public QuestionnaireResList createUserInfoAndAns(int serialNumber, String userName, String phone, String email, String age,
			List<UserAnswer> ansList);
		
	// !未連 拿全部使用者回答 userInfoDaouserInfoDao 0108 OK
	public QuestionnaireRes getAllUserInfo(int serialNumber);
	
	//!未連 問卷回饋 !需要修改!  user 0108
	public QuestionnaireResList getUserInfoAndAns(int userId);
	
	//6統計選項
	public QuestionnaireRes statisticalData(int serialNumber);
}
