package com.example.questionnaire.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
	public QuestionnaireRes deleteQuestionnaire(String caption) {
		Optional<Questionnaire> qestionnaireOp = questionnaireDao.findByCaption(caption);
		if (!qestionnaireOp.isPresent()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_INEXISTED.getMessage());
		}

		return new QuestionnaireRes(QuestionnaireRtnCode.DELETE_SUCCESSFUL.getMessage());
	}

	// 2-1創建問題
	@Override
	public QuestionnaireRes createQuestions(int serialNumber, int questionsId, String questions, boolean questionsType,
			String choose) {
		QuestionnaireRes questionnaireRes = new QuestionnaireRes();
		Optional<Questionnaire> qestionnaire = questionnaireDao.findById(serialNumber);
		if (!qestionnaire.isPresent()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_INEXISTED.getMessage());
		}

//		Optional<Questions> questionsIdOp = questionsDao.findByQuestions(questions);
//		if (questionsIdOp.isPresent()) {
//			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_QUESTIONS_EXISTED.getMessage());
//		}
		Questions questionsInfo = new Questions(serialNumber, questionsId, questions, questionsType, choose);
		questionsDao.save(questionsInfo);
		questionnaireRes.setMessage(QuestionnaireRtnCode.CREATE_SUCCESSFUL.getMessage());
		questionnaireRes.setQuestions(questionsInfo);
		return questionnaireRes;
	}

	// 2-2更改問題
	@Override
	public QuestionnaireRes updateQuestions(int questionsAiId, String questions, boolean questionsType, String choose) {
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

	// 創建使用者資料
	@Override
	public UserInfo createUser(String userName, String phone, String email, String age) {
//		UserInfo users = new UserInfo() ;
		LocalDateTime createTime = LocalDateTime.now();
		UserInfo user = new UserInfo(userName, phone, email, age, createTime);
		return userInfoDao.save(user);
	}

	// 搜尋問卷(依照名稱模糊搜尋)
	@Override
	public QuestionnaireRes findByCaptionContaining(String caption) {
		List<Questionnaire> questionnaireList = questionnaireDao.findByCaptionContaining(caption);
		if (questionnaireList.isEmpty()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.SEARCH_FAIL.getMessage());
		}
		return new QuestionnaireRes(questionnaireList);
	}

	// 搜尋(依照開始日期及結束日期)
	@Override
	public QuestionnaireRes findByStartDateAndEndDateBetween(LocalDate startDate, LocalDate endDate) {
		List<Questionnaire> startList = questionnaireDao.findByStartDateBetween(startDate, endDate);
		List<Questionnaire> endList = questionnaireDao.findByEndDateBetween(startDate, endDate);
		List<Integer> strId = new ArrayList<>();
		for (Questionnaire item : startList) {
			strId.add(item.getSerialNumber()); // strId放入符合開始時間條件的日期
		}
		List<Integer> exitId = new ArrayList<>();
		for (Questionnaire item1 : endList) { // 比對符合結束時間條件的日期
			if (strId.contains(item1.getSerialNumber())) { // 將strId內的Id與item1.getSerialNumber()內的做比對
				exitId.add(item1.getSerialNumber()); // 相同Id都放進exitId
			}
		}
		List<Questionnaire> finallyList = questionnaireDao.findAllBySerialNumberIn(exitId);
		return new QuestionnaireRes(finallyList);
	}

	// 儲存回答者問題
	@Override
	public QuestionnaireRes createAns(int userId, int serialNumber, int questionsId, String choose) {
		Optional<UserInfo> userInfoOp = userInfoDao.findById(userId);
		if (!userInfoOp.isPresent()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.USERID_INEXISTED.getMessage());
		}
		Optional<Questionnaire> questionnaireOp = questionnaireDao.findById(serialNumber);
		if (!questionnaireOp.isPresent()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.QUESTIONNAIER_INEXISTED.getMessage());
		}
		UserAnswer userAnswer = new UserAnswer(userId, serialNumber, questionsId, choose);
		userAnswerDao.save(userAnswer);
		return new QuestionnaireRes(userAnswer, QuestionnaireRtnCode.CREATE_SUCCESSFUL.getMessage());
	}

	// 儲存回答者問題(List)
	@Override
	public QuestionnaireRes createAns1(List<UserAnswer> ansList) {

		userAnswerDao.saveAll(ansList);
		return new QuestionnaireRes(QuestionnaireRtnCode.CREATE_SUCCESSFUL.getMessage(), ansList);
	}

	// 一次儲存
	@Override
	public QuestionnaireResList createUserInfoAndAns(String userName, String phone, String email, String age,
			List<UserAnswer> ansList) {

		LocalDateTime createTime = LocalDateTime.now();
		UserInfo user = new UserInfo();
		UserInfo userInfo = new UserInfo(user.getUserId(), userName, phone, email, age, createTime);
		userInfoDao.save(userInfo);

		for (UserAnswer item : ansList) {
			item.setUserId(userInfo.getUserId());
			userAnswerDao.saveAll(ansList);
		}

		for (UserAnswer item : ansList) {
			item.setAnsAiId(0);
		}

//		List<Questions> questList = questionsDao.findBySerialNumber(serialNumber);
//		for(int i = 0; i < questList.size(); i++) {
//			int questId = questList.get(i).getQuestionsId();
//			
//			UserAnswer userAnswer = new UserAnswer(user.getUserId(), serialNumber, questId, chooseList);
//			userAnswerDao.save(userAnswer);
//		}

		return new QuestionnaireResList(QuestionnaireRtnCode.CREATE_SUCCESSFUL.getMessage(), userInfo, ansList);
	}

	// 統計問題數據
	@Override
	public QuestionnaireRes statisticalData(int serialNumber) {

		List<Questions> qSerailList = questionsDao.findBySerialNumber(serialNumber);
		List<Integer> qQuestionsIdList = new ArrayList<>();
		for (Questions item : qSerailList) {
			qQuestionsIdList.add(item.getQuestionsId());
		}
//
//		Map<String, Integer> questMap = new HashMap<>();
//		for (Integer itemQId : qQuestionsIdList) {
//			List<Questions> qQIdList = questionsDao.findBySerialNumberAndQuestionsId(serialNumber,itemQId);
//			for (Questions chooseItem : qQIdList) {
//				questMap.put(chooseItem.getChoose(), 0);
//			}
//			
//		}

//		Map<String, Integer> ansMap = new HashMap<>();

//		 // 幾筆資料 ()               
//		List<UserAnswer> entryList = userAnswerDao.findBySerialNumber(serialNumber);
//		for(Entry<Integer, Integer> item1: quesMap.entrySet()) {
//			for(UserAnswer item2: entryList) {
//				if(item1.getValue().equals(item2.getQuestionsId())) {
//					 Questions questions = questionsDao.findBySerialNumberAndQuestionsId(serialNumber, item2.getQuestionsId());
//					 String[] questionsArray = questions.getChoose().split(";");
//						for (String arrayItem : questionsArray) {
//							ansMap.put(arrayItem, 0);
//						}
//				}
//			}
//		}

//        
//		
//		
//        for(Questions item : serailList) {
//        	questionsIdList.add(item.getQuestionsId());	
//        	
//        	
//        }
//        Questions questions = new Questions();
//        Questions quest = questionsDao.findBySerialNumberAndQuestionsId(serialNumber, questions.getQuestionsId());
//		String[] questionsArray = questions.getChoose().split(";");
//		for (String arrayItem : questionsArray) {
//			ansMap.put(arrayItem, 0);
//		}

//		// 此問卷此問題總比數
//		int listSize = questList.size();
//
//			
//		for (Entry<String, Integer> item1 : ansMap.entrySet()) {
//			for (UserAnswer item2 : questList) {
//				if (item1.getKey().equals(item2.getChoose())) {
//					int count = item1.getValue();
//					count += 1;
//					item1.setValue(count);
//				}
//			}
//		}
//		    for() {
//		    	
//		    }

		return null;
	}

}
