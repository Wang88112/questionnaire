package com.example.questionnaire.respository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.Questionnaire;

@Repository // 用於資料處理(CRUD)讓SpringBoot託管
public interface QuestionnaireDao extends JpaRepository<Questionnaire, Integer> {

	public Optional<Questionnaire> findByCaption(String caption);

	public List<Questionnaire> findByCaptionContaining(String caption);

	public List<Questionnaire> findByStartDateBetween(LocalDate StratDate, LocalDate EndDate);

	public List<Questionnaire> findByEndDateBetween(LocalDate StratDate, LocalDate EndDate);

//	public List<Questionnaire> findAllBySerialNumberIn(List<Integer> serialNumber);
	
	public List<Questionnaire> findAllBySerialNumberIn(List<Integer> serialNumberList);

}
