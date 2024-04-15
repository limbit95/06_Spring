package edu.kh.project.myPage.model.service;

import java.util.Map;

import edu.kh.project.member.model.dto.Member;

public interface myPageService {

	/** 회원 정보 수정
	 * @param inputMember 
	 * @param memberAddress
	 * @return
	 */
	int updateInfo(Member inputMember, String[] memberAddress);

	/** 비밀번호 수정
	 * @param paramMap
	 * @param memberNo
	 * @return
	 */
	int changePw(Map<String, Object> paramMap, int memberNo);

	/** 회원 탈퇴
	 * @param memberNo
	 * @param memberPw
	 * @return result
	 */ 
	int secession(int memberNo, String memberPw);

	
	
}