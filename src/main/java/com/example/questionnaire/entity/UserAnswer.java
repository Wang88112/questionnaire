package com.example.questionnaire.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "useranswer")
public class UserAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ans_aiid")
	private int ansAiId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "serial_number")
	private int serialNumber;

	@Column(name = "questions_id")
	private int questionsId;

	@Column(name = "choose")
	private String choose;

	public UserAnswer() {

	}

	public UserAnswer(int userId, int serialNumber, int questionsId, String choose) {
		this.userId = userId;
		this.serialNumber = serialNumber;
		this.questionsId = questionsId;
		this.choose = choose;
	}

	public int getAnsAiId() {
		return ansAiId;
	}

	public void setAnsAiId(int ansAiId) {
		this.ansAiId = ansAiId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

}
