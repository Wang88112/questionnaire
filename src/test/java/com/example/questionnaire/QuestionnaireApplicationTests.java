package com.example.questionnaire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.questionnaire.entity.Questionnaire;
import com.example.questionnaire.entity.Questions;
import com.example.questionnaire.entity.UserAnswer;
import com.example.questionnaire.respository.QuestionnaireDao;
import com.example.questionnaire.respository.QuestionsDao;
import com.example.questionnaire.respository.UserAnswerDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class QuestionnaireApplicationTests {

	@Autowired
	private QuestionnaireDao questionnaireDao;

	@Autowired
	private QuestionsDao questionsDao;

	@Autowired
	private UserAnswerDao userAnswerDao;

	@Test
	public void findByCaption() {
		Optional<Questionnaire> questionnaire = questionnaireDao.findByCaption("測試01");
		System.out.println("問卷名稱 : " + questionnaire);
	}

//	@Test
//	public void findByCaption2() {
//		List<Questions> questionsList = questionsDao.findByCaption("測試01");
//		for(Questions item : questionsList) {
//		System.out.printf("問卷名稱 :%s 問題 :%s 選項 :%s\n", item.getQuestionsId() ,item.getQuestions() ,item.getChoose());
//		}
//	}

	@Test
	public void findByCaptionContaining() {
		List<Questionnaire> questionnaireList = questionnaireDao.findByCaptionContaining("1");
		for (Questionnaire item : questionnaireList) {
			System.out.printf("問卷名稱 :%s 內容 :%s 開始日期 :%s 結束日期 :%s\n", item.getCaption(), item.getContent(),
					item.getStartDate(), item.getEndDate());
		}
	}

	@Test
	public void findByStartDateAndEndDateBetween() {
		String str1 = "2022-12-29";
		String str2 = "2023-03-01";
		LocalDate date1 = LocalDate.parse(str1);
		LocalDate date2 = LocalDate.parse(str2);
		List<Questionnaire> questionnaireList = questionnaireDao.findByStartDateBetween(date1, date2);
		List<Questionnaire> questionnaireList2 = questionnaireDao.findByEndDateBetween(date1, date2);
		System.out.println(questionnaireList.size());
		System.out.println(questionnaireList2.size());
	}

//	@Test
//	public void findByCaptionAndQuestions() {
//		List<Questions> findCaptionAndQuestionsList = questionsDao.findByCaptionAndQuestions("", null)
//	}

//	@Test
//	public void findBySerialNumber() {
//		List<Integer> Id = new ArrayList<>();
////		Set<Integer> Id = new HashSet<>();
//		List<Questionnaire> questionnaireList = questionnaireDao.findAllBySerialNumberIn(Id);
//		for(Questionnaire item : questionnaireList) {
//			System.out.printf("問卷名稱 :%s 內容 :%s 開始日期 :%s 結束日期 :%s\n", 
//					item.getCaption() ,item.getContent() ,item.getStartDate(), item.getEndDate());
//			}
//	}

	@Test
	public void statisticalData() {
		List<Questions> serailList = questionsDao.findBySerialNumber(1);
		List<Integer> questionsIdList = new ArrayList<>();
		for (Questions item : serailList) {
			questionsIdList.add(item.getQuestionsId());
		}

//		List<UserAnswer> questList = userAnswerDao.findAllBySerialNumberAndQuestionsIdIn(1, questionsIdList);
//		for (UserAnswer item : questList) {
//			System.out.printf("問卷編號 :%s 問題編號 :%s 問題選項 :%s\n", item.getSerialNumber(), item.getQuestionsId(),
//					item.getChoose());
//		}
	}

	@Test
	public void statisticalData11() throws JsonProcessingException {
		List<Questions> qSerailList = questionsDao.findBySerialNumber(1);

		// QID , (Choose.Entry)
		Map<Integer, Map<String, Integer>> chooseMap = new HashMap<>();

		// questionsDao QID Choose
		Map<Integer, String> qIdMap = new HashMap<>();
		for (Questions item : qSerailList) {
			Questions L = questionsDao.findBySerialNumberAndQuestionsId(1, item.getQuestionsId());
			qIdMap.put(L.getQuestionsId(), L.getChoose());
		}

		Map<String, Integer> cMap = new HashMap<>();
		for (Entry<Integer, String> item : qIdMap.entrySet()) {
			Questions choose = questionsDao.findByQuestionsId(item.getKey());
			String[] questionsArray = choose.getChoose().split(";");
			for (String arrayItem : questionsArray) {
				cMap.put(arrayItem, 0);
			}
		}

//			String[] questionsArray = L.getChoose().split(";");
//			for (String arrayItem : questionsArray) {
//				qIdMap.put(arrayItem, 0);
//			}
//			chooseMap.put(L.getQuestionsId(), qIdMap);

//		// questionsDao QID Choose
//				Map<Integer, String> qIdMap = new HashMap<>();
//				for (Questions item : qSerailList) {
//					Questions L = questionsDao.findBySerialNumberAndQuestionsId(1, item.getQuestionsId());
//					qIdMap.put(L.getQuestionsId(), L.getChoose());
//				}

//		// QID , (Choose.Entry)
//		Map<Integer, Map<String, Integer>> chooseMap = new HashMap<>();
////		Map<String, Integer> questMap = new HashMap<>();
//		// Choose.Entry
//		Map<String, Integer> ansMap = new HashMap<>();
//		List<UserAnswer> entryList = userAnswerDao.findBySerialNumber(1);
//
//		for (Entry<Integer, String> item1 : qIdMap.entrySet()) {
//			for (UserAnswer item2 : entryList) {
//				if(item1.getKey().equals(item2.getQuestionsId())) {
//					String[] questionsArray = item1.getValue().split(";");
//					for (String arrayItem : questionsArray) {
//						ansMap.put(arrayItem, 0);
//						chooseMap.put(item1.getKey(), ansMap);
//					}
//				}
//			}
//		}

//		for (Integer itemQId : qQuestionsIdList) {
//			
//			Questions quest = questionsDao.findBySerialNumberAndQuestionsId(1,itemQId);
//			questMap.put(quest.getChoose(), 0);
//			chooseMap.put(itemQId, questMap);

//				String[] questionsArray = questions.getChoose().split(";");
//				for (String arrayItem : questionsArray) {
//					ansMap.put(arrayItem, 0);
//				}

		ObjectMapper objectMapper = new ObjectMapper();
		String mapStr = objectMapper.writeValueAsString(cMap);
		System.out.println(mapStr);

	}

	@Test
	public void statisticalData1() throws JsonProcessingException {
		List<Questions> serailList = questionsDao.findBySerialNumber(2);
		List<Integer> questionsIdList = new ArrayList<>();
		Map<String, Integer> ansMap = new HashMap<>();

		for (Questions item : serailList) {
			questionsIdList.add(item.getQuestionsId());

			Questions questions = questionsDao.findBySerialNumberAndQuestionsId(2, item.getQuestionsId());
			String[] questionsArray = questions.getChoose().split(";");
			for (String arrayItem : questionsArray) {
				ansMap.put(arrayItem, 0);
			}
		}

//		List<UserAnswer> questList = userAnswerDao.findAllBySerialNumberAndQuestionsIdIn(2, questionsIdList);

		// 此問卷此問題總比數
//		int listSize = questList.size();
//
//		for (Entry<String, Integer> item1 : ansMap.entrySet()) {
//			for (UserAnswer item2 : questList) {
//				if (item1.getKey().equals(item2.getChoose())) {
//					int count = item1.getValue();
//					count += 1;
//					item1.setValue(count);
//				}
//			}
//		}
		ObjectMapper objectMapper = new ObjectMapper();
		String mapStr = objectMapper.writeValueAsString(ansMap);
		System.out.println(mapStr);
	}

	@Test
	public void seachCaptionAndStartDateAndEndDate() {
		String str1 = "2023-01-28";
		String str2 = "2023-01-04";
		LocalDate date1 = LocalDate.parse(str1);
		LocalDate date2 = LocalDate.parse(str2);
		List<Questionnaire> questionnaireList = questionnaireDao.findByCaptionContainingAndEndDateLessThanEqual("測",
				date1);
		for (Questionnaire item : questionnaireList) {
			System.out.println(item.getCaption());
		}
	}

	@Test
	public void findAll() {
		List<Questionnaire> questionnaireList = questionnaireDao.findAll();
		for (Questionnaire item : questionnaireList) {
			System.out.println(item.getCaption());
		}
	}

	@Test // 統計數據
	public void testTest() {
		Map<Integer, Map<String, Integer>> countsMap = new LinkedHashMap<>();

		List<Questions> questionsList = questionsDao.findBySerialNumber(1);
		List<UserAnswer> ansList = userAnswerDao.findBySerialNumber(1);

		for (Questions questions : questionsList) {

			Map<String, Integer> questionChooseMap = new LinkedHashMap<>();

			String[] stringChoiceList = questions.getChoose().split(";"); // 切割選項

			for (String item1 : stringChoiceList) { // 去空白
				questionChooseMap.put(item1.trim(), 0);
			}

			countsMap.put(questions.getQuestionsId(), questionChooseMap);
		}

//		List<UserAnswer> answerList = new ArrayList<>();
//		for(UserAnswer item : answerList) {
//			String[] aList = item.getChoose().split(";"); // 切割選項
//			
//		}

		for (UserAnswer ans : ansList) {

			for (int i = 1; i <= questionsList.size(); i++) {
				if (ans.getQuestionsId() == i) {

					for (Map.Entry<String, Integer> entry : countsMap.get(i).entrySet()) {
						if (entry.getKey().equalsIgnoreCase(ans.getChoose())) {
							entry.setValue(entry.getValue() + 1);
						}
					}
				}
			}
		}

		for (Map.Entry<Integer, Map<String, Integer>> entry : countsMap.entrySet()) {
			System.out.println(entry.getKey());
			for (Map.Entry<String, Integer> entry00 : entry.getValue().entrySet()) {
				System.out.println(entry00.getKey() + "  " + entry00.getValue());

			}
		}
	}

//	@SuppressWarnings("unlikely-arg-type")
	@Test  //統計數據
	public void testTest01() {
		Map<String, Map<String, Integer>> countsMap = new LinkedHashMap<>();

		List<Questions> questionsList = questionsDao.findBySerialNumber(2);
		List<UserAnswer> ansList = userAnswerDao.findBySerialNumber(2);

		for (Questions questions : questionsList) {

			Map<String, Integer>  questionChooseMap = new LinkedHashMap<>();

			String[] stringChoiceList = questions.getChoose().split(";"); // 切割選項

			for (String item1 : stringChoiceList) { // 去空白
				questionChooseMap.put(item1.trim(), 0);
			}

			countsMap.put(questions.getQuestions(), questionChooseMap);
		}
		
		List<String> list = new ArrayList<>();
		
		for(UserAnswer item : ansList) {
			if(item.getChoose().contains(";")) {
				String[] a = item.getChoose().split(";");
				for(String item2 : a) {
					list.add(item2.trim());
				}
			}else {
			list.add(item.getChoose());
			}
		}
		

//		List<UserAnswer> answerList = new ArrayList<>();
//		for(UserAnswer item : answerList) {
//			String[] aList = item.getChoose().split(";"); // 切割選項
//			
//		}
		
		
		
			
			for (int i = 0; i < questionsList.size(); i++) {
//				for (UserAnswer ans : ansList) {
//				if (ans.getQuestionsId() == i) {

					for (Map.Entry<String, Integer> entry : countsMap.get(questionsList.get(i).getQuestions()) .entrySet()) {	
						for(String item : list) {
						if(entry.getKey().equalsIgnoreCase(item)){
							entry.setValue(entry.getValue() + 1);
						}
						}
					}
//				}
//			}
		}


		for (Map.Entry<String, Map<String, Integer>> entry : countsMap.entrySet()) {
			System.out.println(entry.getKey());
			for (Map.Entry<String, Integer> entry00 : entry.getValue().entrySet()) {
				System.out.println(entry00.getKey() + "  " + entry00.getValue());

			}
		}
	}

}
