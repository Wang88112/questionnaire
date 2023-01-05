package com.example.questionnaire.vo;

import java.time.LocalDate;
import java.util.List;

import com.example.questionnaire.entity.UserAnswer;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QuestionnaireReq {

	// questionnaire
	private int serialNumber;

	private String caption;

	private String content;

	private LocalDate startDate;

	private LocalDate endDate;

	// questions
	private int questionsAiId;

	private int questionsId;

	private String questions;

	private boolean questionsType;

	private String choose;

	// user
	private String userName;

	private String phone;

	private String email;

	private String age;

	//userAnswer
	private int userId;
	
	private List<UserAnswer> ansList;

	public QuestionnaireReq() {

	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getQuestionsId() {
		return questionsId;
	}

	public void setQuestionsId(int questionsId) {
		this.questionsId = questionsId;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public boolean isQuestionsType() {
		return questionsType;
	}

	public void setQuestionsType(boolean questionsType) {
		this.questionsType = questionsType;
	}

	public int getQuestionsAiId() {
		return questionsAiId;
	}

	public void setQuestionsAiId(int questionsAiId) {
		this.questionsAiId = questionsAiId;
	}

	public List<UserAnswer> getAnsList() {
		return ansList;
	}

	public void setAnsList(List<UserAnswer> ansList) {
		this.ansList = ansList;
	}

}
