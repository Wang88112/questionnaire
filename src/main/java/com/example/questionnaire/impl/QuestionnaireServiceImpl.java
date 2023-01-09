package com.example.questionnaire.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

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

	// 1創建問卷
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
		questionnaireDao.deleteAll(qestionnaireList);
		questionsDao.deleteAll(questionsList);
		return new QuestionnaireRes(QuestionnaireRtnCode.DELETE_SUCCESSFUL.getMessage());
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
		Questions questionsInfo = new Questions(serialNumber, questionsId, questions, questionsType, choose, chooseType);
		questionsDao.save(questionsInfo);
		questionnaireRes.setMessage(QuestionnaireRtnCode.CREATE_SUCCESSFUL.getMessage());
		questionnaireRes.setQuestions(questionsInfo);
		return questionnaireRes;
	}

	// 2-2更改問題
	@Override
	public QuestionnaireRes updateQuestions(int questionsAiId, String questions, boolean questionsType, String choose, boolean chooseType) {
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
	public QuestionnaireRes deleteQuestions(int questionsId) {
		Optional<Questions> questionsOp = questionsDao.findById(questionsId);
		if (questionsOp.isEmpty()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_QUESTIONS_INEXISTED.getMessage());
		}
		Questions questuons = questionsOp.get();
		questionsDao.delete(questuons);
		return new QuestionnaireRes(QuestionnaireRtnCode.DELETE_SUCCESSFUL.getMessage());
	}

	// 搜尋問卷(依照名稱模糊搜尋)
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

	// 一次儲存
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

//	// 統計問題數據
//	@Override
//	public QuestionnaireRes statisticalData(int serialNumber) {
//		List<Questions> questionsList = questionsDao.findBySerialNumber(serialNumber);
//		// List<Answer> answerList = answerDao.findByqId(qId);
//
//		// 題目編號 , 選項List
//		// Map<Integer, List<String>> choicesMap = new HashMap<>();
//
//		// 題目編號 , 回答List
//		// Map<Integer, List<String>> answersMap = new HashMap<>();
//
//		// 題目編號 , Map<選項, 回答出現的次數>
//		Map<Integer, Map<String, Integer>> countsMap = new HashMap<>();
//
//		Map<Map<Integer, String>, Map<String, Double>> printTotal = new LinkedHashMap<>();
//		int x = 0;
//		// 遍歷題目List
//		for (Questions item : questionsList) {
//			x++;
//			// 回答的答案, 回答出現次數
//			Map<String, Integer> answerMap = new HashMap<>();
//
//			Map<Integer, String> questionMap = new LinkedHashMap<>();
//
//			Map<String, Double> doubleMap = new LinkedHashMap<>();
//
//			questionMap.put(x, item.getQuestions());
//
//			// String[] stringList = item.getChoice().split("；"); // 切割選項
//			// List<String> trimList = new ArrayList<>(); // 切割&去空白後的選項List
//			//
//			// for (String item1 : stringList) { // 去空白
////		    trimList.add(item1.trim());
//			// }
//
//			String[] stringChoiceList = item.getChoose().split("；"); // 切割選項
//			List<String> trimChoiceList = new ArrayList<>(); // 切割&去空白後的選項List
//
//			for (String item1 : stringChoiceList) { // 去空白
//				trimChoiceList.add(item1.trim());
//			}
//
//			// 找出單一題的List
//			List<UserAnswer> userAnswerlist = userAnswerDao.findBySerialNumberAndQuestionsId(item.getSerialNumber(),
//					item.getQuestionsId());
//			List<String> answersList = new ArrayList<>(); // 所有回答的List
//			for (UserAnswer ans : userAnswerlist) {
//				answersList.add(ans.getChoose());
//			}
//			
//			// 所有回答的List，去空白
//			List<String> answerTrimList = new ArrayList<>();
//			for (String ans : answersList) {
//				String[] answerStringList = ans.split("；");
//				for (String trimAnswer : answerStringList) {
//					answerTrimList.add(trimAnswer.trim());
//
//				}
//			}
//			for (String choose : trimChoiceList) {
//				answerTrimList.add(choose);
//			}
//
//			// 比較選項跟所有回答是否匹配，匹配則加1
//
//			for (String ans : answerTrimList) {
//				answerMap.put(ans, answerMap.getOrDefault(ans, 0) + 1); // 放進Map<String, Integer> answerMap
//			}
//
//			for (Map.Entry<String, Integer> entry : answerMap.entrySet()) {
//				entry.setValue(entry.getValue() - 1);
//			}
//
//			for (Map.Entry<String, Integer> entry : answerMap.entrySet()) {
//				String answer = entry.getKey();
//				int count = entry.getValue();
//				double percentsge = 100.0 * count / trimChoiceList.size();
//				doubleMap.put(answer, percentsge);
////		    System.out.println(answer + "：" + percentsge + "％" + "（" + entry.getValue() + "）");
//			}
//
//			countsMap.put(item.getQuestionsId(), answerMap);
//			printTotal.put(questionMap, doubleMap);
//
//		}
//
//		// System.out.println(countsMap);
//		//
//		// for (SList item : sList) {
//		//
//		// String[] stringList = item.getChoice().split("；");
//		// List<String> trimList = new ArrayList<>();
//		//
//		// for (String item1 : stringList) {
////		    trimList.add(item1.trim());
//		// }
//		// choicesMap.put(item.getsId(), trimList);
//		// }
//		//
//		// System.out.println(choicesMap);
//		//
//		// for (Map.Entry<Integer, List<String>> entry : choicesMap.entrySet()) {
//		//
//		// List<Answer> list = answerDao.findBysId(entry.getKey());
//		// List<String> stringList = new ArrayList<>();
//		//
//		// for (Answer item : list) {
////		    stringList.add(item.getAnswer());
//		// }
//		//
//		// answersMap.put(entry.getKey(), stringList);
//		// }
//		//
//		// System.out.println(answersMap);
//
//		// // 回答的答案, 回答出現次數
//		// Map<String, Integer> counts = new HashMap<>();
//		//
//		// int totalCount = 0;
//		//
//		// for (List<String> questionAnswer : answersMap.values()) {
//		// for (String answerCount : questionAnswer) {
////		    counts.put(answerCount, counts.getOrDefault(answerCount, 0) + 1);
//		// }
//		// }
//		//
//		// for (Map.Entry<String, Integer> entry : counts.entrySet()) {
//		// String answer = entry.getKey();
//		// int count = entry.getValue();
//		// double percentsge = 100.0 * count / totalCount;
//		// System.out.println(answer + "：" + percentsge + "％");
//		// }
//		return new QuestionnaireRes(printTotal);
//	}

//	public QuestionnaireRes statisticalData(int serialNumber) {
//		List<Questions> questionsList = questionsDao.findBySerialNumber(serialNumber);
//		
//
//		return null;
//
//	}

	// 取得所有問卷
	@Override
	public QuestionnaireRes getAllCaptions() {
		List<Questionnaire> questionnaireList = questionnaireDao.findAll();
//		LocalDateTime createTime = LocalDateTime.now();
//		for (Questionnaire item : questionnaireList) {
//			String state = "進行中";
//			// 如果開始時間在現在之後 ， state = 尚未開放投票
//			if (item.getStartDate().isAfter(LocalDate.now())) {
//				state = "尚未開始";
//				// 如果開始時間在現在之後 ， state = 結束投票
//			} else if (item.getEndDate().isBefore(LocalDate.now())) {
//				state = "已結束";
//				// 除此之外都是顯示投票中
//			} else {
//				state = "投票中";
//			}
//		}
//		questionnaireDao.saveAll();
		return new QuestionnaireRes(questionnaireList);
	}

	// 問卷回饋 user
	@Override
	public QuestionnaireResList getUserInfoAndAns(int userId) {
		Optional<UserInfo> userInfoOp = userInfoDao.findById(userId);
		List<UserAnswer> ansList = userAnswerDao.findByUserId(userId);
		return new QuestionnaireResList(QuestionnaireRtnCode.SEARCH_SUCCESSFUL.getMessage(), userInfoOp, ansList);
	}

	// 拿全部使用者回答 userInfoDao !需要修改!
	@Override
	public QuestionnaireRes getAllUserInfo(int serialNumber) {
		List<UserInfo> userList = userInfoDao.findBySerialNumber(serialNumber);
		return new QuestionnaireRes(userList, QuestionnaireRtnCode.SEARCH_SUCCESSFUL.getMessage());
	}

	// 顯示問卷問題
	@Override
	public QuestionnaireResList getAllQuestions(int serialNumber) {
		Questionnaire questionnaier = questionnaireDao.findById(serialNumber).get();
		List<Questions> questionsList = questionsDao.findBySerialNumber(serialNumber);
		return new QuestionnaireResList(questionnaier, questionsList);
	}
}
