package com.example.questionnaire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
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

	@Column(name = "questions_type")
	private boolean questionsType;

	@Column(name = "choose")
	private String choose;

	public Questions() {

	}

	public Questions(int serialNumber, int questionsId, String questions, boolean questionsType, String choose) {
		this.serialNumber = serialNumber;
		this.questionsId = questionsId;
		this.questions = questions;
		this.questionsType = questionsType;
		this.choose = choose;
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

}
