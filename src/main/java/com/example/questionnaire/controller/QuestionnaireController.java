package com.example.questionnaire.controller;



import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionnaire.constants.QuestionnaireRtnCode;
import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.UserAnswer;
import com.example.questionnaire.entity.UserInfo;
import com.example.questionnaire.ifs.QuestionnaireService;
import com.example.questionnaire.vo.QuestionnaireReq;
import com.example.questionnaire.vo.QuestionnaireRes;
import com.example.questionnaire.vo.QuestionnaireResList;

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
		if(req.getSerialNumber() <= 0) {
			return new QuestionnaireRes(QuestionnaireRtnCode.SERIALNUMBER_EMPTY.getMessage());
		}
		LocalDate d = LocalDate.now();
		if (req.getStartDate().isBefore(d)) {
			return new QuestionnaireRes(QuestionnaireRtnCode.STAETTIME_NOW.getMessage());
		}

		if (req.getStartDate().isAfter(req.getEndDate())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.STAETTIME_ERROR.getMessage());
		}
//        if(!StringUtils.hasText(req.getCaption()) && !StringUtils.hasText(req.getContent()) && 
//		    req.getStartDate() == null && req.getEndDate() == null) {
//	        return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_CONTENT_STRATTIME_ENDTIME_EMPTY.getMessage());
//}
        QuestionnaireRes questionnaire = questionnaireService.updateQuestionnaire(req.getSerialNumber(), 
        		req.getCaption(), req.getContent(), req.getStartDate(), req.getEndDate());
		return questionnaire;
	}
	
	//1-3刪除問卷(需修改成不林值控制開放與否)
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
		QuestionnaireRes questions = questionnaireService.createQuestions(req.getSerialNumber(), req.getQuestionsId()
				, req.getQuestions(), req.isQuestionsType(), req.getChoose());
		return questions;

	}

	// 2-1創建問題的空字串判斷
	private QuestionnaireRes checkParam2(QuestionnaireReq req) {
		if (req.getSerialNumber() <=0) {
			return new QuestionnaireRes(QuestionnaireRtnCode.SERIALNUMBER_EMPTY.getMessage());
		}
		if (req.getQuestionsId() <=0) {
			return new QuestionnaireRes(QuestionnaireRtnCode.QUESTIONS_EMPTY.getMessage());
		}
		if (!StringUtils.hasText(req.getQuestions())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.QUESTIONS_EMPTY.getMessage());
		}
		if (!StringUtils.hasText(req.getChoose())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CHOOSE_EMPTY.getMessage());
		}
		return null;
	}

	// 2-2更新問題
	@PostMapping(value = "/api/updateQuestions")
	public QuestionnaireRes updateQuestions(@RequestBody QuestionnaireReq req) {
		if (req.getQuestionsAiId() <= 0) {
			return new QuestionnaireRes(QuestionnaireRtnCode.QUESTIONSAIID_EMPTY.getMessage());
		}
		if (!StringUtils.hasText(req.getQuestions())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.QUESTIONS_EMPTY.getMessage());
		}
		if (!StringUtils.hasText(req.getChoose())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CHOOSE_EMPTY.getMessage());
		}
		QuestionnaireRes questions = questionnaireService.updateQuestions(req.getQuestionsAiId(), req.getQuestions(),
				req.isQuestionsType(), req.getChoose());
		return questions;
	}
	
	//2-3刪除問題
	@PostMapping(value = "/api/deleteQuestions")
	public QuestionnaireRes deleteQuestions(@RequestBody QuestionnaireReq req) {
		if(req.getQuestionsId() <= 0) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_EMPTY.getMessage());
		}
	
		QuestionnaireRes questions = questionnaireService.deleteQuestions(req.getQuestionsId());
		return questions;
	}

	// 3.創建使用者
	@PostMapping(value = "/api/createUser")
	private QuestionnaireRes createUser(@RequestBody QuestionnaireReq req) {
		String userEmail = "[A-Za-z0-9+_.-]+@(.+)$";
		if (!StringUtils.hasLength(req.getUserName())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.USERNAME_EMPTY.getMessage());
		}else if(!StringUtils.hasText(req.getPhone())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.PHONE_EMPTY.getMessage());
		}else if(!StringUtils.hasText(req.getEmail())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.EMAIL_EMPTY.getMessage());
		}else if(!StringUtils.hasText(req.getAge())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.AGE_EMPTY.getMessage());
		}else if(!req.getPhone().matches("\\d{2}-\\d{3}-\\d{4}|\\d{2}-\\d{4}-\\d{4}")) {
			return new QuestionnaireRes(QuestionnaireRtnCode.PHONE_ERROR.getMessage());
		}else if(!req.getEmail().matches(userEmail)) {
			return new QuestionnaireRes(QuestionnaireRtnCode.EMAIL_ERROR.getMessage());
		}
		UserInfo user = questionnaireService.createUser(req.getUserName(), req.getPhone(), 
				req.getEmail(), req.getAge());
		return new QuestionnaireRes(QuestionnaireRtnCode.CREATE_SUCCESSFUL.getMessage(), user);
	}
	
	//搜尋問卷(透過問卷名稱模糊搜尋)
	@PostMapping(value = "/api/findByCaptionContaining")
	public QuestionnaireRes findByCaptionContaining(@RequestBody QuestionnaireReq req) {
		if(!StringUtils.hasText(req.getCaption())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CAPTION_EMPTY.getMessage());
		}
		QuestionnaireRes questuonnaire = questionnaireService.findByCaptionContaining(req.getCaption());
		return questuonnaire;
	}
	
	//搜尋問卷(透過時間)
	@PostMapping(value = "/api/findByStartDateAndEndDateBetween")
	public QuestionnaireRes findByStartDateAndEndDateBetween(@RequestBody QuestionnaireReq req) {
		if ((req.getStartDate() == null)) {
			return new QuestionnaireRes(QuestionnaireRtnCode.STRATTIME_EMPTY.getMessage());
		}
		if (req.getEndDate() == null) {
			return new QuestionnaireRes(QuestionnaireRtnCode.ENDTIME_EMPTY.getMessage());
		}
		return questionnaireService.findByStartDateAndEndDateBetween(req.getStartDate(), req.getEndDate());
	}
	
	//儲存回答者問題
	@PostMapping(value = "/api/createAns")
	public QuestionnaireRes createAns(@RequestBody QuestionnaireReq req) {
		if(req.getUserId() <= 0) {
			return new QuestionnaireRes(QuestionnaireRtnCode.USERID_EMPTY.getMessage());
		}
		if (req.getSerialNumber() <=0) {
			return new QuestionnaireRes(QuestionnaireRtnCode.SERIALNUMBER_EMPTY.getMessage());
		}
		if (req.getQuestionsId() <=0) {
			return new QuestionnaireRes(QuestionnaireRtnCode.QUESTIONSID_EMPTY.getMessage());
		}
		if(!StringUtils.hasText(req.getChoose())) {
			return new QuestionnaireRes(QuestionnaireRtnCode.CHOOSE_EMPTY.getMessage());
		}
		return questionnaireService.createAns(req.getUserId(), req.getSerialNumber(), req.getQuestionsId(), req.getChoose()) ;		
	}
	
	//儲存回答者問題(List)
	@PostMapping(value = "/api/createAnsList")
	public QuestionnaireRes createAns1(@RequestBody List<UserAnswer> ansList){
		return questionnaireService.createAns1(ansList);		
	}
	
	//儲存回答者問題(全部)
	@PostMapping(value = "/api/createUserInfoAndAns")
	public QuestionnaireResList createUserInfoAndAns(@RequestBody QuestionnaireReq req) {
		return questionnaireService.createUserInfoAndAns(req.getUserName(), req.getPhone(), req.getEmail(), req.getAge(), req.getAnsList());
		
	}
	
}
