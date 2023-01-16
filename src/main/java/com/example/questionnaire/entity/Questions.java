package com.example.questionnaire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "questions_aiid")
	private int questionsAiId;

	@Column(name = "serial_number")
	private int serialNumber;

	@Column(name = "questions_id")
	private int questionsId;

	@Column(name = "questions")
	private String questions;

	//是否為必選
	@Column(name = "questions_type")
	private boolean questionsType;

	@Column(name = "choose")
	private String choose;

	//是否為多選
	@Column(name = "choose_type")
	private boolean chooseType;

	public Questions() {

	}

	public Questions(int serialNumber, int questionsId, String questions, boolean questionsType, String choose, boolean chooseType) {
		this.serialNumber = serialNumber;
		this.questionsId = questionsId;
		this.questions = questions;
		this.questionsType = questionsType;
		this.choose = choose;
		this.chooseType = chooseType;
	}
	
	public int getQuestionsId() {
		return questionsId;
	}

	public void setQuestionsId(int questionsId) {
		this.questionsId = questionsId;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public int getQuestionsAiId() {
		return questionsAiId;
	}

	public void setQuestionsAiId(int questionsAiId) {
		this.questionsAiId = questionsAiId;
	}

	public boolean isQuestionsType() {
		return questionsType;
	}

	public void setQuestionsType(boolean questionsType) {
		this.questionsType = questionsType;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public boolean isChooseType() {
		return chooseType;
	}

	public void setChooseType(boolean chooseType) {
		this.chooseType = chooseType;
	}

}
