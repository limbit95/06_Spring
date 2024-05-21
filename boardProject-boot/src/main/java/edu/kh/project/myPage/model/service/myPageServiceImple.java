package edu.kh.project.myPage.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.common.util.Utility;
import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.dto.UploadFile;
import edu.kh.project.myPage.model.mapper.myPageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class) // Exception.class 명시 안할 시 기본값은 RuntimeException임
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:/config.properties")
public class myPageServiceImple implements myPageService{

	private final myPageMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
	@Value("${my.profile.web-path}")
	private String profileWebPath; 		// /myPage/profile
	@Value("${my.profile.folder-path}")
	private String profileFolderPath; 	// C:/uploadFiles/profile/

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
	public List<String> fileUpload1(List<MultipartFile> uploadFile) throws Exception{
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
		
		List<String> pathList = new ArrayList<String>();
		
		for(int i = 0; i < uploadFile.size(); i++) {
			uploadFile.get(i).transferTo(new File("C:\\uploadFiles\\test\\" + uploadFile.get(i).getOriginalFilename()));
			pathList.add("/myPage/file/" + uploadFile.get(i).getOriginalFilename());
		}
		
		// 업로드한 파일이 있을 경우
		// C:/uploadFiles/test/파일명 으로 서버에 저장
		
		// 웹에서 해당 파일에 접근할 수 있는 경로를 반환
		
		// 서버 : C:\\uploadFiles\\test\\a.jpg
		// 웹 접근 주소 : /myPage/file/a.jpg
		
		return pathList;
	}

	// 파일 업로드 테스트 2(+DB)
	@Override
	public int fileUpload2(MultipartFile uploadFile, int memberNo) throws IOException{
		// 업로드된 파일이 없다면
		// == 선택된 파일이 없을 경우
		if(uploadFile.isEmpty()) {
			return 0;
		}
		
		// DB에 파일 저장이 가능은 하지만
		// DB 부하를 줄이기 위해서 
		// - 1) DB에는 서버에 저장할 파일 경로를 저장
		// - 2) DB 삽입/수정 성공 후 서버에 파일을 저장
		// - 3) 만약, 파일 저장 싪패 시
		// 		-> 예외 발생
		//		-> @Transactional을 이용해서 rollback 수행
		
		// 1. 서버에 저장할 파일 경로
		// - 파일이 저장될 서버 폴더 경로
		String folderPath = "C:/uploadFiles/test/";
		
		// - 클라이언트가 파일이 저장된 폴더에 접근할 수 있는 주소
		String webPath = "/myPage/file/";
		
		// 2. DB에 전달할 데이터를 DTO로 묶어서 INSERT 호출하기
		// webPath, memberNo, 원본 파일명, 변경된 파일명
		
		// 원본 파일명 -> 변경된 파일명(Utility 클래스의 fileRename 메서드를 통한 파일명 변경)
		String fileRename = Utility.fileRename(uploadFile.getOriginalFilename());
		
		// Builder 패턴을 이용해서 UploadFile 객체 생성
		// 장점
		// - 1) 반복되는 참조변수명, set 구문 생략
		// - 2) method chaining을 이용해서 한 줄로 작성 가능
		UploadFile uf = UploadFile.builder()
						.filePath(webPath)
						.fileOriginalName(uploadFile.getOriginalFilename())
						.fileRename(fileRename)
						.memberNo(memberNo)
						.build();
		
		int result = mapper.insertUploadFile(uf);
		
		// 3. 삽입(INSERT) 성공 시 파일을 지정된 서버 폴더에 저장
		if(result == 0) return 0;
		
		// C:/uploadFiles/test/변경된 파일명으로
		// 파일을 서버 컴퓨터에 저장
		uploadFile.transferTo(new File(folderPath + fileRename));
		// 					 C:/uploadFiles/test/ + 20240417111605_00001.png
		// -> CheckedException 발생 -> 예외 처리 필수
		
		// @Transactional은 RuntimeException(UncheckedException의 대표)만 처리
		// -> rollbackFor 속성 이용해서
		// rollback 할 예외 범위를 수정
		
		return result;
	}

	// 파일 목록 조회
	@Override
	public List<UploadFile> fileList() {
		return mapper.fileList();
	}

	// 여러 파일 업로드
	@Override
	public int fileUpload3(List<MultipartFile> aaaList, List<MultipartFile> bbbList, int memberNo) throws Exception{
		// 1. aaaList 처리
		int result1 = 0;
		
		// 업로드된 파일이 없을 경우를 제외하고 업로드
		for(MultipartFile file : aaaList) {
			if(file.isEmpty()) {
				continue;
			}
			// fileUpload2() 메서드 호출 (재활용)
			// -> 파일 하나 업로드 + DB INSERT
			result1 += fileUpload2(file, memberNo);
		}
		
		int result2 = 0;
		// 2. bbbList 처리
		for(MultipartFile file : bbbList) {
			if(file.isEmpty()) {
				continue;
			}
			result2 += fileUpload2(file, memberNo);
		}
		
		return result1 + result2;
	}

	// 프로필 이미지 변경
	@Override
	public int profile(MultipartFile profileImg, Member loginMember) throws Exception{
		// 수정할 경로
		String updatePath = null;
		
		// 변경명 저장
		String rename = null;
		
		// 업로드한 이미지 있을 경우
		// - 있을 경우 : 수정할 경로 조합 (클라이언트 접근 경로 + Rename 파일명)
		if(!profileImg.isEmpty()) {
			// updatePath 조합
			
			// 1. 파일명 변경
			rename = Utility.fileRename(profileImg.getOriginalFilename());
			
			// 2. /myPage/profile/변경된파일명
			updatePath = profileWebPath + rename;
		}
		
		// 수정된 프로필 이미지 경로 + 회원 번호를 저장할 DTO 객체
		Member mem = Member.builder()
					 .memberNo(loginMember.getMemberNo())
					 .profileImg(updatePath)
					 .build();
		
		// UPDATE 수행
		int result = mapper.profile(mem);
		
		if(result > 0) {
			// 프로필 이미지를 없앤 경우(NULL로 수정한 경우)를 제외
			// -> 업로드한 이미지가 있을 경우
			if(!profileImg.isEmpty()) {
				// 파일을 서버에 지정된 폴더에 저장
				profileImg.transferTo(new File(profileFolderPath + rename));
			}
			
			// 세션 회원 정보에서 프로필 이미지 경로를 
			// 업데이트한 경로로 변경
			loginMember.setProfileImg(updatePath);
		}
		
		return result;
	}

	
	

	
}