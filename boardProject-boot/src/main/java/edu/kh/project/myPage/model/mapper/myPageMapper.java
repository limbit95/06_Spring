package edu.kh.project.myPage.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.dto.UploadFile;

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
	int checkNickname(Member inputMember);

	/** 파일 업로드 테스트 2(+DB)
	 * @param uf
	 * @return result
	 */
	int insertUploadFile(UploadFile uf);

	/** 파일 목록 조회
	 * @return
	 */
	List<UploadFile> fileList();

	/** 프로필 이미지 변경
	 * @param mem
	 * @return
	 */
	int profile(Member mem);


	
	
}