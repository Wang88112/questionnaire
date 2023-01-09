package com.example.questionnaire.respository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.Questionnaire;

@Repository // 用於資料處理(CRUD)讓SpringBoot託管
public interface QuestionnaireDao extends JpaRepository<Questionnaire, Integer> {

	public Optional<Questionnaire> findByCaption(String caption);

	public List<Questionnaire> findByStartDateBetween(LocalDate StratDate, LocalDate EndDate);

	public List<Questionnaire> findByEndDateBetween(LocalDate StratDate, LocalDate EndDate);

//	public List<Questionnaire> findAllBySerialNumberIn(List<Integer> serialNumber);
	
	public List<Questionnaire> findAllBySerialNumberIn(List<Integer> serialNumberList);
	
//	@Query("SELECT caption FROM questionnaire ORDER BY start_date DESC")
	public List<Questionnaire> findBySerialNumber(int serialNumber);
	
	//名稱模糊搜尋
	public List<Questionnaire> findByCaptionContaining(String caption);
	//名稱+開始+結束
	public List<Questionnaire> findByCaptionContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(String caption, LocalDate StratDate, LocalDate EndDate);
	//名稱+開始
	public List<Questionnaire> findByCaptionContainingAndStartDateGreaterThanEqual(String caption, LocalDate StratDate);
	//名稱+結束
	public List<Questionnaire> findByCaptionContainingAndEndDateLessThanEqual(String caption, LocalDate EndDate);
	//開始+結束
	public List<Questionnaire> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate StratDate, LocalDate EndDate);
	//開始
	public List<Questionnaire> findByStartDateGreaterThanEqual(LocalDate StratDate);
	//結束
	public List<Questionnaire> findByEndDateLessThanEqual(LocalDate EndDate);

}
