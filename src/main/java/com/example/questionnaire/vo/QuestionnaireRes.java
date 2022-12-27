package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.Questions;
import com.example.questionnaire.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionnaireRes {

	private Questionnaire questionnaire;

	private Questions questions;
	
	private User user;
	
	private String message;

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

	public QuestionnaireRes(String message, User user) {
		this.message = message;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
