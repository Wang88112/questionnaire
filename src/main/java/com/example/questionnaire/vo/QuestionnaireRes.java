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
public class QuestionnaireRes {

	private Questionnaire questionnaire;

	private Questions questions;

	private UserInfo user;

	private String message;

	private UserAnswer userAnswer;

	private QuestionnaireResList questionnaireList;

	private List<Questionnaire> questList;

	private List<UserAnswer> ansList;

	private List<QuestionnaireRes> resList;

	private List<UserInfo> userList;

	private List<Questions> questionsList;

	private Optional<UserInfo> userInfoOp;

	private Map<String, Map<String, Integer>> printTotal;
	
	

	public QuestionnaireRes() {
	}

	public QuestionnaireRes(String message) {
		this.message = message;
	}

	public QuestionnaireRes(String message, Questionnaire questionnaire) {
		this.message = message;
		this.questionnaire = questionnaire;
	}

	public QuestionnaireRes(String message, Questions questions) {
		this.message = message;
		this.questions = questions;
	}

	public QuestionnaireRes(String message, UserInfo user) {
		this.message = message;
		this.user = user;
	}

	public QuestionnaireRes(UserAnswer userAnswer, String message) {
		this.userAnswer = userAnswer;
		this.message = message;
	}

	public QuestionnaireRes(List<Questionnaire> questList) {
		this.questList = questList;
	}

	public QuestionnaireRes(String message, UserInfo user, UserAnswer userAnswer) {
		this.message = message;
		this.user = user;
		this.userAnswer = userAnswer;
	}

	public QuestionnaireRes(String message, List<UserAnswer> ansList) {
		this.message = message;
		this.ansList = ansList;
	}

	public QuestionnaireRes(String message, UserInfo user, List<UserAnswer> ansList) {
		this.message = message;
		this.user = user;
		this.ansList = ansList;
	}


	public QuestionnaireRes(List<UserInfo> userList, String message) {
		this.userList = userList;
		this.message = message;
	}

	public void questionsList(List<Questions> questionsList) {
		this.questionsList = questionsList;
	}

	public List<UserAnswer> getAnsList() {
		return ansList;
	}

	public void setAnsList(List<UserAnswer> ansList) {
		this.ansList = ansList;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public UserAnswer getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(UserAnswer userAnswer) {
		this.userAnswer = userAnswer;
	}

	public QuestionnaireResList getQuestionnaireList() {
		return questionnaireList;
	}

	public void setQuestionnaireList(QuestionnaireResList questionnaireList) {
		this.questionnaireList = questionnaireList;
	}

	public List<Questionnaire> getQuestList() {
		return questList;
	}

	public void setQuestList(List<Questionnaire> questList) {
		this.questList = questList;
	}

	public List<QuestionnaireRes> getResList() {
		return resList;
	}

	public void setResList(List<QuestionnaireRes> resList) {
		this.resList = resList;
	}

	public List<UserInfo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfo> userList) {
		this.userList = userList;
	}

	public List<Questions> getQuestionsList() {
		return questionsList;
	}

	public void setQuestionsList(List<Questions> questionsList) {
		this.questionsList = questionsList;
	}

	public Optional<UserInfo> getUserInfoOp() {
		return userInfoOp;
	}

	public void setUserInfoOp(Optional<UserInfo> userInfoOp) {
		this.userInfoOp = userInfoOp;
	}

	public Map<String, Map<String, Integer>> getPrintTotal() {
		return printTotal;
	}

	public void setPrintTotal(Map<String, Map<String, Integer>> printTotal) {
		this.printTotal = printTotal;
	}

}
