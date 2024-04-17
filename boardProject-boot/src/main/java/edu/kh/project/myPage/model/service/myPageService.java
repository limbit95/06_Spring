package edu.kh.project.myPage.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.dto.UploadFile;

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
	List<String> fileUpload1(List<MultipartFile> uploadFile) throws Exception;

	/** 파일 업로드 테스트 2(+DB)
	 * @param uploadFile
	 * @param memberNo
	 * @return
	 */
	int fileUpload2(MultipartFile uploadFile, int memberNo) throws IOException;

	/** 파일 목록 조회
	 * @return
	 */
	List<UploadFile> fileList();

	/** 여러 파일 업로드
	 * @param aaaList
	 * @param bbbList
	 * @param memberNo
	 * @return
	 */
	int fileUpload3(List<MultipartFile> aaaList, List<MultipartFile> bbbList, int memberNo) throws Exception;

	/** 프로필 이미지 변경
	 * @param profileImg
	 * @param loginMember
	 * @return
	 */
	int profile(MultipartFile profileImg, Member loginMember) throws Exception;


	
	
}