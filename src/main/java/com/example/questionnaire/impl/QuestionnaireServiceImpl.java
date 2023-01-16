package com.example.questionnaire.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.questionnaire.constants.QuestionnaireRtnCode;
import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.Questions;
import com.example.questionnaire.entity.UserAnswer;
import com.example.questionnaire.entity.UserInfo;
import com.example.questionnaire.ifs.QuestionnaireService;
import com.example.questionnaire.respository.QuestionnaireDao;
import com.example.questionnaire.respository.QuestionsDao;
import com.example.questionnaire.respository.UserAnswerDao;
import com.example.questionnaire.respository.UserInfoDao;
import com.example.questionnaire.vo.QuestionnaireRes;
import com.example.questionnaire.vo.QuestionnaireResList;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	private QuestionnaireDao questionnaireDao;

	@Autowired
	private QuestionsDao questionsDao;

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private UserAnswerDao userAnswerDao;

	// 1-1創建問卷
	@Override
	public Questionnaire createQuestionnaire(String caption, String content, LocalDate startDate, LocalDate endDate) {
		Questionnaire qestionnaire = new Questionnaire(caption, content, startDate, endDate);
		return questionnaireDao.save(qestionnaire);
	}

	// 1-2更改問卷
	@Override
	public QuestionnaireRes updateQuestionnaire(int serialNumber, String caption, String content, LocalDate startDate,
			LocalDate endDate) {
		QuestionnaireRes questionnaireRes = new QuestionnaireRes();
		Optional<Questionnaire> qestionnaire = questionnaireDao.findById(serialNumber);
		if (!qestionnaire.isPresent()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_INEXISTED.getMessage());
		}
		Questionnaire quest = qestionnaire.get();

		if (StringUtils.hasText(caption)) {
			quest.setCaption(caption);
		}
		if (StringUtils.hasText(content)) {
			quest.setContent(content);
		}
		if (startDate != null) {
			quest.setStartDate(startDate);
		}
		if (endDate != null) {
			quest.setEndDate(endDate);
		}
		questionnaireDao.save(quest);
		questionnaireRes.setMessage(QuestionnaireRtnCode.UPDATE_SUCCESSFUL.getMessage());
		questionnaireRes.setQuestionnaire(quest);
		return questionnaireRes;
	}

	// 1-3刪除問卷
	@Override
	public QuestionnaireRes deleteQuestionnaire(int serialNumber) {
		List<Questionnaire> qestionnaireList = questionnaireDao.findBySerialNumber(serialNumber);
		if (qestionnaireList.isEmpty()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_INEXISTED.getMessage());
		}
		List<Questions> questionsList = questionsDao.findBySerialNumber(serialNumber);
		List<UserInfo> userInfoList = userInfoDao.findBySerialNumber(serialNumber);
		List<UserAnswer> userAnswerList = userAnswerDao.findBySerialNumber(serialNumber);
		questionnaireDao.deleteAll(qestionnaireList);
		questionsDao.deleteAll(questionsList);
		userInfoDao.deleteAll(userInfoList);
		userAnswerDao.deleteAll(userAnswerList);
		return new QuestionnaireRes(QuestionnaireRtnCode.DELETE_SUCCESSFUL.getMessage());
	}

	// 1-4取得所有問卷
	@Override
	public QuestionnaireRes getAllCaptions() {
		List<Questionnaire> questionnaireList = questionnaireDao.findAll();
		if(questionnaireList.isEmpty()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.USERANSWER_INEXISTED.getMessage());
		}
//			LocalDateTime createTime = LocalDateTime.now();
//			for (Questionnaire item : questionnaireList) {
//				String state = "進行中";
//				// 如果開始時間在現在之後 ， state = 尚未開放投票
//				if (item.getStartDate().isAfter(LocalDate.now())) {
//					state = "尚未開始";
//					// 如果開始時間在現在之後 ， state = 結束投票
//				} else if (item.getEndDate().isBefore(LocalDate.now())) {
//					state = "已結束";
//					// 除此之外都是顯示投票中
//				} else {
//					state = "投票中";
//				}
//			}
//			questionnaireDao.saveAll();
		return new QuestionnaireRes(questionnaireList);
	}

	// 2-1創建問題
	@Override
	public QuestionnaireRes createQuestions(int serialNumber, int questionsId, String questions, boolean questionsType,
			String choose, boolean chooseType) {
		QuestionnaireRes questionnaireRes = new QuestionnaireRes();
		Optional<Questionnaire> qestionnaire = questionnaireDao.findById(serialNumber);
		if (!qestionnaire.isPresent()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_INEXISTED.getMessage());
		}

//		Optional<Questions> questionsIdOp = questionsDao.findByQuestions(questions);
//		if (questionsIdOp.isPresent()) {
//			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_QUESTIONS_EXISTED.getMessage());
//		}
		Questions questionsInfo = new Questions(serialNumber, questionsId, questions, questionsType, choose,
				chooseType);
		questionsDao.save(questionsInfo);
		questionnaireRes.setMessage(QuestionnaireRtnCode.CREATE_SUCCESSFUL.getMessage());
		questionnaireRes.setQuestions(questionsInfo);
		return questionnaireRes;
	}

	// 2-2更改問題
	@Override
	public QuestionnaireRes updateQuestions(int questionsAiId, String questions, boolean questionsType, String choose,
			boolean chooseType) {
		QuestionnaireRes questionnaireRes = new QuestionnaireRes();
		Optional<Questions> questionsOp = questionsDao.findById(questionsAiId);
		if (!questionsOp.isPresent()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.QUESTIONSID_INEXISTED.getMessage());
		}
		Questions question = questionsOp.get();
		if (StringUtils.hasText(questions)) {
			question.setQuestions(questions);
		}
		if (StringUtils.hasText(choose)) {
			question.setChoose(choose);
		}
		question.setQuestionsType(questionsType);
		question.setChooseType(chooseType);
		questionsDao.save(question);
		questionnaireRes.setMessage(QuestionnaireRtnCode.UPDATE_SUCCESSFUL.getMessage());
		questionnaireRes.setQuestions(question);
		return questionnaireRes;
	}

	// 2-3刪除問題
	@Override
	public QuestionnaireRes deleteQuestions(int questionsAiId) {
		Optional<Questions> questionsOp = questionsDao.findById(questionsAiId);
		if (questionsOp.isEmpty()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_QUESTIONS_INEXISTED.getMessage());
		}
		Questions questuons = questionsOp.get();
		questionsDao.delete(questuons);
		List<UserAnswer> userAnswerList = userAnswerDao.findByQuestionsAiId(questionsAiId);
		userAnswerDao.deleteAll(userAnswerList);
		return new QuestionnaireRes(QuestionnaireRtnCode.DELETE_SUCCESSFUL.getMessage());
	}

	// 2-4顯示問卷問題
	@Override
	public QuestionnaireResList getAllQuestions(int serialNumber) {
		Questionnaire questionnaier = questionnaireDao.findById(serialNumber).get();
		List<Questions> questionsList = questionsDao.findBySerialNumber(serialNumber);
		if(questionsList.isEmpty()) {
			return new QuestionnaireResList(QuestionnaireRtnCode.SEARCH_FAIL.getMessage());
		}
		return new QuestionnaireResList(questionnaier, questionsList);
	}

	// 3-1一次儲存使用者資料及答案
	@Override
	public QuestionnaireResList createUserInfoAndAns(int serialNumber, String userName, String phone, String email,
			String age, List<UserAnswer> ansList) {

		LocalDateTime createTime = LocalDateTime.now();
		UserInfo user = new UserInfo();
		UserInfo userInfo = new UserInfo(user.getUserId(), serialNumber, userName, phone, email, age, createTime);
		userInfoDao.save(userInfo);

		for (UserAnswer item : ansList) {
			item.setUserId(userInfo.getUserId());
			userAnswerDao.saveAll(ansList);
		}

		for (UserAnswer item : ansList) {
			item.setAnsAiId(0);
		}

		return new QuestionnaireResList(QuestionnaireRtnCode.CREATE_SUCCESSFUL.getMessage(), userInfo, ansList);
	}

	// 3-2拿全部使用者回答 userInfoDao
	@Override
	public QuestionnaireRes getAllUserInfo(int serialNumber) {
		List<UserInfo> userList = userInfoDao.findBySerialNumber(serialNumber);
		if(userList.isEmpty()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.SEARCH_FAIL.getMessage());
		}
		return new QuestionnaireRes(userList, QuestionnaireRtnCode.SEARCH_SUCCESSFUL.getMessage());
	}

	// 4搜尋問卷(依照名稱模糊搜尋)
	@Override
	public QuestionnaireRes findByCaptionContainingAndStartDateAndEndDate(String caption, LocalDate startDate,
			LocalDate endDate) {
		// 搜尋全部
		if (!StringUtils.hasText(caption) && startDate == null && endDate == null) {
			List<Questionnaire> questionnaureList = questionnaireDao.findAll();
			return new QuestionnaireRes(questionnaureList);
		}
		// 名稱模糊搜尋
		else if (StringUtils.hasText(caption) && startDate == null && endDate == null) {
			List<Questionnaire> questionnaureList = questionnaireDao.findByCaptionContaining(caption);
			return new QuestionnaireRes(questionnaureList);
		}
		// 名稱+開始+結束
		else if (StringUtils.hasText(caption) && startDate != null && endDate != null) {
			List<Questionnaire> questionnaureList = questionnaireDao
					.findByCaptionContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(caption, startDate,
							endDate);
			return new QuestionnaireRes(questionnaureList);
		}
		// 名稱+開始
		else if (StringUtils.hasText(caption) && startDate != null && endDate == null) {
			List<Questionnaire> questionnaureList = questionnaireDao
					.findByCaptionContainingAndStartDateGreaterThanEqual(caption, startDate);
			return new QuestionnaireRes(questionnaureList);
		}
		// 名稱+結束
		else if (StringUtils.hasText(caption) && startDate == null && endDate != null) {
			List<Questionnaire> questionnaureList = questionnaireDao
					.findByCaptionContainingAndStartDateGreaterThanEqual(caption, endDate);
			return new QuestionnaireRes(questionnaureList);
		}
		// 開始+結束
		else if (!StringUtils.hasText(caption) && startDate != null && endDate != null) {
			List<Questionnaire> questionnaureList = questionnaireDao
					.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
			return new QuestionnaireRes(questionnaureList);
		}
		// 開始
		else if (!StringUtils.hasText(caption) && startDate != null && endDate == null) {
			List<Questionnaire> questionnaureList = questionnaireDao.findByStartDateGreaterThanEqual(startDate);
			return new QuestionnaireRes(questionnaureList);
		}
		// 結束
		else if (!StringUtils.hasText(caption) && startDate == null && endDate != null) {
			List<Questionnaire> questionnaireList = questionnaireDao.findByEndDateLessThanEqual(endDate);
			return new QuestionnaireRes(questionnaireList);
		}

		return new QuestionnaireRes(QuestionnaireRtnCode.SEARCH_FAIL.getMessage());
		// 回復查無結果
	}

	// 5問卷回饋 user
	@Override
	public QuestionnaireResList getUserInfoAndAns(int userId) {
		Optional<UserInfo> userInfoOp = userInfoDao.findById(userId);
        if(!userInfoOp.isPresent()) {
        	return new QuestionnaireResList(QuestionnaireRtnCode.SEARCH_FAIL.getMessage());
        }
		int serialNumber = userInfoOp.get().getSerialNumber();

		Map<String, Map<String, Integer>> countsMap = new LinkedHashMap<>();

		List<Questions> questionsList = questionsDao.findBySerialNumber(serialNumber);// 在questionsDao裡找到此問卷所有問題
		List<UserAnswer> ansList = userAnswerDao.findByUserId(userId); // 在userAnswerDao裡找到回答此問卷的答案

		for (Questions questions : questionsList) { // 先對問題的選項進行切割

			Map<String, Integer> questionChooseMap = new LinkedHashMap<>();

			String[] stringChoiceList = questions.getChoose().split(";"); // 切割選項

			for (String item1 : stringChoiceList) { // 去空白
				questionChooseMap.put(item1.trim(), 0); // 將切割好的選項放入questionChooseMap的Key值
			}

			countsMap.put(questions.getQuestions(), questionChooseMap); // 將題號放入countsMap的Key值
		}

		List<String> list = new ArrayList<>(); // 創新的List來放接切完的答案

		for (UserAnswer item : ansList) { // 遍歷ansList
			if (item.getChoose().contains(";")) { //
				String[] a = item.getChoose().split(";");
				for (String item2 : a) {
					list.add(item2.trim());
				}
			} else {
				list.add(item.getChoose());
			}
		}

		for (int i = 0; i < questionsList.size(); i++) {
			for (Map.Entry<String, Integer> entry : countsMap.get(questionsList.get(i).getQuestions()).entrySet()) {
				for (String item : list) {
					if (entry.getKey().equalsIgnoreCase(item)) {
						entry.setValue(entry.getValue() + 1);
					}
				}
			}
		}

		QuestionnaireResList res = new QuestionnaireResList();
		res.setUserInfoOp(userInfoOp);
		res.setPrintTotal(countsMap);
		res.setMessage(QuestionnaireRtnCode.SEARCH_SUCCESSFUL.getMessage());
		return res;
	}

	// 6統計問題數據
	@Override
	public QuestionnaireRes statisticalData(int serialNumber) {
		Map<String, Map<String, Integer>> countsMap = new LinkedHashMap<>();

		List<Questions> questionsList = questionsDao.findBySerialNumber(serialNumber);// 在questionsDao裡找到此問卷所有問題
		List<UserAnswer> ansList = userAnswerDao.findBySerialNumber(serialNumber); // 在userAnswerDao裡找到回答此問卷的答案

		for (Questions questions : questionsList) { // 先對問題的選項進行切割

			Map<String, Integer> questionChooseMap = new LinkedHashMap<>();

			String[] stringChoiceList = questions.getChoose().split(";"); // 切割選項

			for (String item1 : stringChoiceList) { // 去空白
				questionChooseMap.put(item1.trim(), 0); // 將切割好的選項放入questionChooseMap的Key值
			}

			countsMap.put(questions.getQuestions(), questionChooseMap); // 將題號放入countsMap的Key值
		}

		List<String> list = new ArrayList<>(); // 創新的List來放接切完的答案

		for (UserAnswer item : ansList) { // 遍歷ansList
			if (item.getChoose().contains(";")) { //
				String[] a = item.getChoose().split(";");
				for (String item2 : a) {
					list.add(item2.trim());
				}
			} else {
				list.add(item.getChoose());
			}
		}

		for (int i = 0; i < questionsList.size(); i++) {
			for (Map.Entry<String, Integer> entry : countsMap.get(questionsList.get(i).getQuestions()).entrySet()) {
				for (String item : list) {
					if (entry.getKey().equalsIgnoreCase(item)) {
						entry.setValue(entry.getValue() + 1);
					}
				}
			}
		}

		QuestionnaireRes res = new QuestionnaireRes();
		res.setPrintTotal(countsMap);
		return res;
	}

}
