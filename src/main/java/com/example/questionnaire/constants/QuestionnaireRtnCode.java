package com.example.questionnaire.constants;

public enum QuestionnaireRtnCode {

	CREATE_SUCCESSFUL("200", "新增成功。"), 
	UPDATE_SUCCESSFUL("200", "修改成功。"),
	DELETE_SUCCESSFUL("200", "刪除成功。"),
	SEARCH_SUCCESSFUL("200", "搜尋成功。"),
	SEARCH_FAIL("200", "查無結果!"),
	CAPTION_EMPTY("400", "問卷名稱不得為空!"),
	CONTENT_EMPTY("400", "描述內容不得為空!"),
	STRATTIME_EMPTY("400", "開始時間不得為空!"),
	ENDTIME_EMPTY("400", "結束時間不得為空!"),
	CAPTION_INEXISTED("400", "問卷名稱不存在!"),
	QUESTIONNAIER_INEXISTED("400", "問卷不存在!"),
	QUESTIONS_EMPTY("400", "問題不得為空!"),
	CHOOSE_EMPTY("400", "選項不得為空!"),
	USERNAME_EMPTY("400", "姓名不得為空!"),
	PHONE_EMPTY("400", "電話不得為空!"),
	EMAIL_EMPTY("400", "電子郵件不得為空!"),
	AGE_EMPTY("400", "年齡不得為空!"),
	PHONE_ERROR("400", "電話格式錯誤!"),
	EMAIL_ERROR("400", "電子郵件格式錯誤!"),
	STAETTIME_ERROR("400", "開始時間不得晚於結束時間!"),
	STAETTIME_NOW("400", "開始時間不得晚於現在時間!"),
	CAPTION_QUESTIONS_EXISTED("400", "此問卷問題已存在!"),
	CAPTION_QUESTIONS_INEXISTED("400", "此問卷問題不存在!"),
	CAPTION_REQUIRED("400", "問卷名稱必填!"),
	CAPTION_CONTENT_STRATTIME_ENDTIME_EMPTY("400", "新問卷名稱、內容、開始時間、結束時間其中一項不得為空!"),
	QUESTIONS_CHOOSE_EMPTY("400", "問題、選項不得皆為空!"),
	SERIALNUMBER_EMPTY("400", "問卷編號不得為空!"),
	USERID_EMPTY("400", "使用者編號不得為空!"),
	QUESTIONSAIID_EMPTY("400", "題目編號不得為空!"),
	QUESTIONSID_INEXISTED("400", "題目編號不存在!"),
	QUESTIONSID_EMPTY("400", "問題編號不得為空!"),
	USERID_INEXISTED("400", "使用者編號不存在!")
	;
	
	private String code;
	private String message;
	
	private QuestionnaireRtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
