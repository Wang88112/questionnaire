package com.example.questionnaire.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.UserAnswer;
import com.example.questionnaire.vo.QuestionnaireRes;
import com.example.questionnaire.vo.QuestionnaireResList;

public interface QuestionnaireService {

	// 1-1創建問卷
	public Questionnaire createQuestionnaire(String caption, String content, LocalDate startDate, LocalDate endDate);

	// 1-2更新問卷
	public QuestionnaireRes updateQuestionnaire(int serialNumber, String caption, String content, LocalDate startDate,
			LocalDate endDate);

	// 1-3刪除問卷(需修改成布林值控制(先不改))
	public QuestionnaireRes deleteQuestionnaire(int serialNumber);

	// 1-4拿全部問卷資料
	public QuestionnaireRes getAllCaptions();

	// 2-1創建問題
	public QuestionnaireRes createQuestions(int serialNumber, int questionsId, String questions, boolean questionsType,
			String choose, boolean chooseType);

	// 2-2更新問題
	public QuestionnaireRes updateQuestions(int questionsAiId, String questions, boolean questionsType, String choose,
			boolean chooseType);

	// 2-3刪除問題(需修改成布林值控制(先不改))
	public QuestionnaireRes deleteQuestions(int questionsAiId);

	// 2-4顯示問卷問題
	public QuestionnaireResList getAllQuestions(int serialNumber);

	// 3-1一次儲存使用者及問卷回答
	public QuestionnaireResList createUserInfoAndAns(int serialNumber, String userName, String phone, String email,
			String age, List<UserAnswer> ansList);

	// 3-2拿全部使用者回答 (userInfoDao userInfoDao)
	public QuestionnaireRes getAllUserInfo(int serialNumber);

	// 4搜尋(依照問卷名稱、開始以及結束時間模糊搜尋)
	public QuestionnaireRes findByCaptionContainingAndStartDateAndEndDate(String caption, LocalDate startDate,
			LocalDate endDate);

	// 5問卷回饋
	public QuestionnaireResList getUserInfoAndAns(int userId);

	// 6統計選項
	public QuestionnaireRes statisticalData(int serialNumber);
}
