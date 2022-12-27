package com.example.questionnaire.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class QuestionsId implements Serializable{
	
	private String caption;

	private String questions;

	public QuestionsId() {

	}

	public QuestionsId(String caption, String questions) {
		this.caption = caption;
		this.questions = questions;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}



}
