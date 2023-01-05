package com.example.questionnaire.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionnaire.entity.UserInfo;

@Repository  //用於資料處理(CRUD)讓SpringBoot託管
public interface UserInfoDao extends JpaRepository<UserInfo, Integer>{

	public List<UserInfo> findAllByUserIdIn(List<Integer> userIdList);
}
