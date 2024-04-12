package edu.kh.project.email.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailMapper {

	/** 인증번호 업데이트
	 * @param map
	 * @return
	 */
	int updateAuthKey(Map<String, String> map);

	/**
	 * @param map
	 * @return
	 */
	int insertAuthKey(Map<String, String> map);

	/** 이메일, 인증번호 확인
	 * @param map 
	 * @return
	 */
	int checkAuthKey(Map<String, Object> map);

	
	
}