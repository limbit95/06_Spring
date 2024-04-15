package edu.kh.project.myPage.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;

@Mapper
public interface myPageMapper {

	/** 회원 정보 수정
	 * @param inputMember
	 * @return result
	 */
	int updateInfo(Member inputMember);

	/** 회원의 비밀번호 조회(암호화된 비밀번호 조회)
	 * @param memberNo
	 * @return 암호회된 비밀번호
	 */
	String selectPw(int memberNo);

	/** 비밀번호 변경 (암호화된 비밀번호로 변경)
	 * @param member
	 * @return
	 */
	int changePw(Map<String, Object> paramMap);

	/** 회원 탈퇴
	 * @param loginMember
	 * @return result
	 */
	int secession(int memberNo);

	/** 닉네임 중복 검사 (회원 정보 수정 과정 중에 일어나는)
	 * @param inputMember
	 * @return
	 */
	int checkNickname(String inputMember);

	
	
}