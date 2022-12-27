package com.example.questionnaire.controller;



import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionnaire.constants.QuestionnaireRtnCode;
import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.User;
import com.example.questionnaire.ifs.QuestionnaireService;
import com.example.questionnaire.vo.QuestionnaireReq;
import com.example.questionnaire.vo.QuestionnaireRes;

@CrossOrigin
@RestController
public class QuestionnaireController {

	@Autowired
	private QuestionnaireService questionnaireService;

	// 1-1創建問卷
	@PostMapping(value = "/api/createQuestionnaire")
	public QuestionnaireRes createQuestionnaire(@RequestBody QuestionnaireReq req) {
		QuestionnaireRes checkResult = checkParam(req);
		if (checkResult != null) {
			return checkResult;
		}

		LocalDate d = LocalDate.now();
		if (req.getStartDate().isBefore(d)) {
			return new QuestionnaireRes(QuestionnaireRtnCode.STAETTIME_NOW.getMessage());
		}

		if (req.getStartDate().isAfter(req.getEndDate())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.STAETTIME_ERROR.getMessage());
		}
		Questionnaire questionnaire = questionnaireService.createQuestionnaire(req.getCaption(), req.getContent(),
				req.getStartDate(), req.getEndDate());
		return new QuestionnaireRes(QuestionnaireRtnCode.CREATE_SUCCESSFUL.getMessage(), questionnaire);
	}

	// 1-1創建問卷的空字串判斷
	private QuestionnaireRes checkParam(QuestionnaireReq req) {
		if (!StringUtils.hasText(req.getCaption())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_EMPTY.getMessage());
		} else if (!StringUtils.hasText(req.getContent())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CONTENT_EMPTY.getMessage());
		}
		if ((req.getStartDate() == null)) {
			return new QuestionnaireRes(QuestionnaireRtnCode.STRATTIME_EMPTY.getMessage());
		}
		if (req.getEndDate() == null) {
			return new QuestionnaireRes(QuestionnaireRtnCode.ENDTIME_EMPTY.getMessage());
		}
		return null;
	}

	// 1-2更改問卷
	@PostMapping(value = "/api/updateQuestionnaire")
	public QuestionnaireRes updateQuestionnaire(@RequestBody QuestionnaireReq req) {
		if(!StringUtils.hasText(req.getCaption())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_REQUIRED.getMessage());
		}
		LocalDate d = LocalDate.now();
		if (req.getStartDate().isBefore(d)) {
			return new QuestionnaireRes(QuestionnaireRtnCode.STAETTIME_NOW.getMessage());
		}

		if (req.getStartDate().isAfter(req.getEndDate())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.STAETTIME_ERROR.getMessage());
		}
        if(!StringUtils.hasText(req.getNewCaption()) && !StringUtils.hasText(req.getContent()) && 
		    req.getStartDate() == null && req.getEndDate() == null) {
	        return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_CONTENT_STRATTIME_ENDTIME_EMPTY.getMessage());
}
        QuestionnaireRes questionnaire = questionnaireService.updateQuestionnaire(req.getCaption(), 
				req.getNewCaption(), req.getContent(), req.getStartDate(), req.getEndDate());
		return questionnaire;
	}
	
	//1-3刪除問卷
	@PostMapping(value = "/api/deleteQuestionnaire")
	public QuestionnaireRes deleteQuestionnaire(@RequestBody QuestionnaireReq req) {
		if(!StringUtils.hasText(req.getCaption())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_EMPTY.getMessage());
		}
		QuestionnaireRes questionnaire = questionnaireService.deleteQuestionnaire(req.getCaption());
		return questionnaire;
	}

	// 2-1創建問題
	@PostMapping(value = "/api/createQuestions")
	public QuestionnaireRes createQuestions(@RequestBody QuestionnaireReq req) {
		QuestionnaireRes checkResult = checkParam2(req);
		if (checkResult != null) {
			return checkResult;
		}
		QuestionnaireRes questions = questionnaireService.createQuestions(req.getCaption(), req.getQuestions(),
				req.getOptions());
		return questions;

	}

	// 2-1創建問題的空字串判斷
	private QuestionnaireRes checkParam2(QuestionnaireReq req) {
		if (!StringUtils.hasText(req.getCaption())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_EMPTY.getMessage());
		} else if (!StringUtils.hasText(req.getQuestions())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.QUESTIONS_EMPTY.getMessage());
		} else if (!StringUtils.hasText(req.getOptions())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.OPTIONS_EMPTY.getMessage());
		}
		return null;
	}

	// 2-2更新問題
	@PostMapping(value = "/api/updateQuestions")
	public QuestionnaireRes updateQuestions(@RequestBody QuestionnaireReq req) {
		if (!StringUtils.hasText(req.getCaption())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_EMPTY.getMessage());
		}
		if (!StringUtils.hasText(req.getQuestions())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.QUESTIONS_EMPTY.getMessage());
		}
		if (!StringUtils.hasText(req.getNewQuestions()) && !StringUtils.hasText(req.getOptions())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.QUESTIONS_OPTIONS_EMPTY.getMessage());
		}
		QuestionnaireRes questions = questionnaireService.updateQuestions(req.getCaption(), req.getQuestions(),
				req.getNewQuestions(), req.getOptions());
		return questions;
	}
	
	//2-3刪除問題
	@PostMapping(value = "/api/deleteQuestions")
	public QuestionnaireRes deleteQuestions(@RequestBody QuestionnaireReq req) {
		if(!StringUtils.hasText(req.getCaption())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_EMPTY.getMessage());
		}
		if(!StringUtils.hasText(req.getQuestions())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.QUESTIONS_EMPTY.getMessage());
		}
		QuestionnaireRes questions = questionnaireService.deleteQuestions(req.getCaption(), req.getQuestions());
		return questions;
	}

	// 3.創建使用者
	@PostMapping(value = "/api/createUser")
	private QuestionnaireRes createUser(@RequestBody QuestionnaireReq req) {
		if (!StringUtils.hasLength(req.getUserName())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.USERNAME_EMPTY.getMessage());
		}
		User user = questionnaireService.createUser(req.getUserName());
		return new QuestionnaireRes(QuestionnaireRtnCode.CREATE_SUCCESSFUL.getMessage(), user);
	}
}
