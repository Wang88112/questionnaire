package com.example.questionnaire.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.questionnaire.constants.QuestionnaireRtnCode;
import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.Questions;
import com.example.questionnaire.entity.QuestionsId;
import com.example.questionnaire.entity.User;
import com.example.questionnaire.ifs.QuestionnaireService;
import com.example.questionnaire.respository.QuestionnaireDao;
import com.example.questionnaire.respository.QuestionsDao;
import com.example.questionnaire.respository.UserDao;
import com.example.questionnaire.vo.QuestionnaireRes;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	private QuestionnaireDao questionnaireDao;

	@Autowired
	private QuestionsDao questionsDao;

	@Autowired
	private UserDao userDao;

	//1創建問卷
	@Override
	public Questionnaire createQuestionnaire(String caption, String content, LocalDate startDate, LocalDate endDate) {
		Questionnaire qestionnaire = new Questionnaire(caption, content, startDate, endDate);
		return questionnaireDao.save(qestionnaire);
	}

	//1-2更改問卷
	@Override
	public QuestionnaireRes updateQuestionnaire(String caption, String newCaption, String content, LocalDate startDate,
			LocalDate endDate) {
		QuestionnaireRes questionnaireRes = new QuestionnaireRes();
		Optional<Questionnaire> qestionnaire = questionnaireDao.findByCaption(caption);
		if (!qestionnaire.isPresent()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_INEXISTED.getMessage());
		}
		Questionnaire quest = qestionnaire.get();

		if (StringUtils.hasText(newCaption)) {
			quest.setCaption(newCaption);
			
			List<Questions> qestionsList = questionsDao.findByCaption(caption);
			for(Questions qestions : qestionsList) {
				questionsDao.delete(qestions);
				qestions.setCaption(newCaption);
				questionsDao.save(qestions);
			}
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

	//1-3刪除問卷
	@Override
	public QuestionnaireRes deleteQuestionnaire(String caption) {
		Optional<Questionnaire> qestionnaireOp = questionnaireDao.findByCaption(caption);
		if (!qestionnaireOp.isPresent()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_INEXISTED.getMessage());
		}
		Questionnaire questionnaire = qestionnaireOp.get();
		questionnaireDao.delete(questionnaire);
		List<Questions> qestionsList = questionsDao.findByCaption(caption);
		questionsDao.deleteAll(qestionsList);
		
		return new QuestionnaireRes(QuestionnaireRtnCode.DELETE_SUCCESSFUL.getMessage());
	}
	
	//2-1創建問題
	@Override
	public QuestionnaireRes createQuestions(String caption, String questions, String options) {
		QuestionnaireRes questionnaireRes = new QuestionnaireRes();
		Optional<Questionnaire> qestionnaire = questionnaireDao.findByCaption(caption);
		if (!qestionnaire.isPresent()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_INEXISTED.getMessage());
		}

		QuestionsId questionsId = new QuestionsId(caption, questions);
		Optional<Questions> questionsIdOp = questionsDao.findById(questionsId);
		if (questionsIdOp.isPresent()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_QUESTIONS_EXISTED.getMessage());
		}
		Questions questionsInfo = new Questions(caption, questions, options);
		questionsDao.save(questionsInfo);
		questionnaireRes.setMessage(QuestionnaireRtnCode.CREATE_SUCCESSFUL.getMessage());
		questionnaireRes.setQuestions(questionsInfo);
		return questionnaireRes;
	}

	//2-2更改問題
	@Override
	public QuestionnaireRes updateQuestions(String caption, String questions, String newQuestions, String options) {
		QuestionnaireRes questionnaireRes = new QuestionnaireRes();
		QuestionsId questionsId = new QuestionsId(caption, questions);
		Optional<Questions> questionsOp = questionsDao.findById(questionsId);
		if (questionsOp.isEmpty()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_QUESTIONS_INEXISTED.getMessage());
		}
		Questions question = questionsOp.get();
		if(StringUtils.hasText(newQuestions)) {
			questionsDao.delete(question);
			question.setQuestions(newQuestions);
		}
		if(StringUtils.hasText(options)) {
			question.setOptions(options);
		}
		questionsDao.save(question);
		questionnaireRes.setMessage(QuestionnaireRtnCode.UPDATE_SUCCESSFUL.getMessage());
		questionnaireRes.setQuestions(question);
		return questionnaireRes;
	}
	
	//2-3刪除問題
	@Override
	public QuestionnaireRes deleteQuestions(String caption, String questions) {
		QuestionsId questionsId = new QuestionsId(caption, questions);
		Optional<Questions> questionsOp = questionsDao.findById(questionsId);
		if (questionsOp.isEmpty()) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_QUESTIONS_INEXISTED.getMessage());
		}
		Questions questuons = questionsOp.get();
		questionsDao.delete(questuons);
		return new QuestionnaireRes(QuestionnaireRtnCode.DELETE_SUCCESSFUL.getMessage());
	}
	
	// 取得使用者資料
	@Override
	public User createUser(String userName) {
		LocalDateTime createTime = LocalDateTime.now();
		User user = new User(userName, createTime);
		return userDao.save(user);
	}

	@Override
	public QuestionnaireRes findCaptionAndStartDateAndEndDate(String caption, LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	

	



}
