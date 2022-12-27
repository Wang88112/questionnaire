package com.example.questionnaire.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QuestionnaireReq {

	// questionnaire
	private String caption;

	private String content;

	private LocalDate startDate;

	private LocalDate endDate;

	private String newCaption;

	// questions
	private String questions;

	private String potions;

	private String newQuestions;

	// user
	private String userName;

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

	public String getNewCaption() {
		return newCaption;
	}

	public void setNewCaption(String newCaption) {
		this.newCaption = newCaption;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getPotions() {
		return potions;
	}

	public void setPotions(String potions) {
		this.potions = potions;
	}

	public String getNewQuestions() {
		return newQuestions;
	}

	public void setNewQuestions(String newQuestions) {
		this.newQuestions = newQuestions;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
