package com.example.questionnaire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
@IdClass(QuestionsId.class)
public class Questions {
	
	@Id
	@Column(name = "caption")
	private String caption;
	
	@Id
	@Column(name = "questions")
	private String questions;

	@Column(name = "options")
	private String options;

	public Questions() {

	}

	public Questions(String caption, String questions, String options) {
		this.caption = caption;
		this.questions = questions;
		this.options = options;
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

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	

}
