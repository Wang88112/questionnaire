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

	@Column(name = "potions")
	private String potions;

	public Questions() {

	}

	public Questions(String caption, String questions, String potions) {
		this.caption = caption;
		this.questions = questions;
		this.potions = potions;
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

	public String getPotions() {
		return potions;
	}

	public void setPotions(String potions) {
		this.potions = potions;
	}

}
