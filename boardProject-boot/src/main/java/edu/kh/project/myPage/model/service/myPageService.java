package edu.kh.project.myPage.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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

	/** 파일 업로드 테스트 1
	 * @param uploadFile
	 * @return path
	 */
	String fileUpload1(MultipartFile uploadFile) throws Exception;

	
	
}