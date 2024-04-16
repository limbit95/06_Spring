package edu.kh.project.myPage.model.service;

import java.io.File;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.mapper.myPageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class) // Exception.class 명시 안할 시 기본값은 RuntimeException임
@RequiredArgsConstructor
@Slf4j
public class myPageServiceImple implements myPageService{

	private final myPageMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;

	// 회원 정보 수정
	@Override
	public int updateInfo(Member inputMember, String[] memberAddress) {
		
		// 주소 입력 X -> inputMember.getMemberAddress() -> ",,"
		if( inputMember.getMemberAddress().equals(",,") ) {
			inputMember.setMemberAddress(null);
		} else { // 입력된 주소가 있을 경우
			// memberAddress 를 A^^^B^^^C 형태로 가공
			String address = String.join("^^^", memberAddress);
			inputMember.setMemberAddress(address);
		}
		
		int result = mapper.checkNickname(inputMember);
		
		if(result > 0) {
			return -1;
		}
		
		return mapper.updateInfo(inputMember);
	}

	// 비밀번호 수정
	@Override
	public int changePw(Map<String, Object> paramMap, int memberNo) {
		// 현재 로그인한 회원의 암호화된 비밀번호를 DB에서 조회
		String originPw = mapper.selectPw(memberNo);
		
		// 입력 받은 현재 비밀번호와 (평문)
		// DB에서 조회한 비밀번호 비교(암호화)
		// BCryptPasswordEncoder.matches(평문, 암호화된 비밀번호)
		
		// 다를 경우
		// 
		if( !bcrypt.matches((String)paramMap.get("currentPw"), originPw) ) {
			return 0;
		}
		// bcrypt.matches() 를 사용할 때 () 안에 
		// 첫 번째 매개변수로는 평문
		// 두 번째 매개변수로는 암호문을 넣어야 함
		// 순서 바꾸면 해당 메서드 작동 안함
		
		// 같은 경우
		// 새 비밀번호 암호화 진행
		String encPw = bcrypt.encode((String)paramMap.get("newPw"));
		
		paramMap.put("encPw", encPw);
		paramMap.put("memberNo", memberNo);
		
		return mapper.changePw(paramMap);
	}

	// 회원 탈퇴
	@Override
	public int secession(int memberNo, String memberPw) {
		// 현재 로그인한 회원의 암호화된 비밀번호를 DB에서 조회
		String originPw = mapper.selectPw(memberNo);
		
		// 다를 경우
		if( !bcrypt.matches(memberPw, originPw) ) {
			return 0;
		}
		
		return mapper.secession(memberNo);
	}

	// 파일 업로드 테스트 1
	@Override
	public String fileUpload1(MultipartFile uploadFile) throws Exception{
		// MultipartFile이 제공하는 메서드
		// - getSize() : 파일 크기
		// - isEmpty() : 업로드한 파일이 없을 경우 true 반환
		// - getOriginalFileName() : 원본 파일명
		// - transferTo(경로) : 메모리 또는 임시 저장 경로에 업로드된 파일을
		//						원하는 경로에 전송(서버의 어떤 폴더에 저장할지를 지정)
		//						(DB에 문제 없이 커밋되었을 경우 transferTo() 를 사용)
		
		// 업로드한 파일이 없을 경우
		if(uploadFile.isEmpty()) { 
			return null;
		}
		
		// 업로드한 파일이 있을 경우
		// C:/uploadFiles/test/파일명 으로 서버에 저장
		uploadFile.transferTo(new File("C:\\uploadFiles\\test\\" + uploadFile.getOriginalFilename()));
		
		// 웹에서 해당 파일에 접근할 수 있는 경로를 반환
		
		// 서버 : C:\\uploadFiles\\test\\a.jpg
		// 웹 접근 주소 : /myPage/file/a.jpg
		
		return "/myPage/file/" + uploadFile.getOriginalFilename();
	}

	
}