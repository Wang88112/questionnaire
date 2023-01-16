package com.example.questionnaire.vo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.Questions;
import com.example.questionnaire.entity.UserAnswer;
import com.example.questionnaire.entity.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionnaireResList {
	
	private Questionnaire questionnaire;
	
	private UserInfo userInfo;

	private List<UserAnswer> ansList;

	private Optional<UserInfo> userInfoOp;

	private Optional<Questionnaire> questionnaier;

	private List<Questions> questionsList;
	
	private Map<String, Map<String, Integer>> printTotal;

	private String message;

	public QuestionnaireResList() {

	}

	public QuestionnaireResList(String message, UserInfo userInfo, List<UserAnswer> ansList) {
		this.message = message;
		this.userInfo = userInfo;
		this.ansList = ansList;
	}

	public QuestionnaireResList(String message, Optional<UserInfo> userInfoOp, List<UserAnswer> ansList) {
		this.message = message;
		this.userInfoOp = userInfoOp;
		this.ansList = ansList;
	}

	public QuestionnaireResList(Optional<Questionnaire> questionnaier, List<Questions> questionsList) {
		this.questionnaier = questionnaier;
		this.questionsList = questionsList;
	}

	public QuestionnaireResList(Questionnaire questionnaire, List<Questions> questionsList) {
		this.questionnaire = questionnaire;
		this.questionsList = questionsList;
	}
	

	public QuestionnaireResList(String message, UserInfo userInfo, Map<String, Map<String, Integer>> printTotal) {
		this.message = message;
		this.userInfo = userInfo;
		this.printTotal = printTotal;
	}

	public QuestionnaireResList(String message) {
		this.message = message;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<UserAnswer> getAnsList() {
		return ansList;
	}

	public void setAnsList(List<UserAnswer> ansList) {
		this.ansList = ansList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Optional<UserInfo> getUserInfoOp() {
		return userInfoOp;
	}

	public void setUserInfoOp(Optional<UserInfo> userInfoOp) {
		this.userInfoOp = userInfoOp;
	}

	public Optional<Questionnaire> getQuestionnaier() {
		return questionnaier;
	}

	public void setQuestionnaier(Optional<Questionnaire> questionnaier) {
		this.questionnaier = questionnaier;
	}

	public List<Questions> getQuestionsList() {
		return questionsList;
	}

	public void setQuestionsList(List<Questions> questionsList) {
		this.questionsList = questionsList;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Map<String, Map<String, Integer>> getPrintTotal() {
		return printTotal;
	}

	public void setPrintTotal(Map<String, Map<String, Integer>> printTotal) {
		this.printTotal = printTotal;
	}

	
}
