package com.example.questionnaire.vo;

import java.util.List;

import com.example.questionnaire.entity.UserAnswer;
import com.example.questionnaire.entity.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionnaireResList {

	private UserInfo userInfo;

	private List<UserAnswer> ansList;

	private String message;

	public QuestionnaireResList() {

	}

	public QuestionnaireResList(String message, UserInfo userInfo, List<UserAnswer> ansList) {
		this.message = message;
		this.userInfo = userInfo;
		this.ansList = ansList;
	}

	public QuestionnaireResList(String message) {
		this.message = message;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<UserAnswer> getAnsList() {
		return ansList;
	}

	public void setAnsList(List<UserAnswer> ansList) {
		this.ansList = ansList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
