package com.example.questionnaire.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Answer {

	private int userId;

	private int serialNumber;

	private int questionsId;

	private String choose;

	public Answer() {

	}

	public Answer(int userId, int serialNumber, int questionsId, String choose) {
		super();
		this.userId = userId;
		this.serialNumber = serialNumber;
		this.questionsId = questionsId;
		this.choose = choose;
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
