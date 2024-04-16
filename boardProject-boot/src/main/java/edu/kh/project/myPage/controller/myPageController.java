package edu.kh.project.myPage.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.service.myPageService;
import lombok.RequiredArgsConstructor;


// 세션에 올라가 있는 정보를 다른 컨트롤러에서 꺼내올때 해당 컨트롤러 클래스명 위에
// @SessionAttributes 어노테이션도 달아줘야 함
@SessionAttributes({"loginMember"})  
@Controller
@RequiredArgsConstructor
@RequestMapping("myPage")
public class myPageController {
	
	private final myPageService service;

	/** 내 정보 조회/수정 화면으로 전환
	 * @param loginMember : 세션에 존재하는 loginMember를 얻어와 매개변수에 대입
	 * @param model : 데이터 전달용 객체(기본 request scope)
	 * @return myPage/myPage-info로 요청위임
	 */
	@GetMapping("info")
	public String Info(@SessionAttribute("loginMember") Member loginMember,
					   Model model) {
		
		// 주소 꺼내옴
		String memberAddress = loginMember.getMemberAddress();
		
		// 주소가 있을 경우에만 동작
		if(memberAddress != null) {
			// 구분자 "^^^" 를 기준으로
			// memberAddress 값을 쪼개어 String[]로 반환
			String[] arr = memberAddress.split("\\^\\^\\^");
			
			// "04540^^^서울 중구 남대문로 120^^^3층 E 강의장"
			// --> ["04540", "서울 중구 남대문로 120", "3층 E 강의장"]
			model.addAttribute("postcode", arr[0]);
			model.addAttribute("address", arr[1]);
			model.addAttribute("detailAddress", arr[2]);
		}
		
		return "myPage/myPage-info";
	}
	 
	/** 회원 정보 수정 
	 * @param inputMember : 제출된 회원의 닉네임, 전화번호, 주소(,,)
	 * @param loginMembe : 로그인한 회원 정보(회원 번호 사용할 예정)
	 * @param memberAddress : 주소만 따로 받은 String[]
	 * @param ra : Redirect 시 request scope로 데이터 전달
	 * @return redirect:info
	 */
	@PostMapping("info")
	public String updateInfo(Member inputMember,
							 @SessionAttribute("loginMember") Member loginMember,
							 @RequestParam("memberAddress") String[] memberAddress,
							 RedirectAttributes ra) {
		
		// inputMember에 로그인한 회원번호 추가
		int memberNo = loginMember.getMemberNo();
		inputMember.setMemberNo(memberNo);
		
		// 회원 정보 수정 서비스 호출
		int result = service.updateInfo(inputMember, memberAddress);
		
		String message = null;
		
		if(result > 0) {
			message = "회원 정보 수정 성공";
			
			// loginMember는
			// session에 저장된 로그인한 회원 정보가 저장된 객체를 참조하고 있음
			
			// -> loginMember를 수정하면
			//	  session에 저장된 로그인한 회원 정보가 수정됨
			
			// == session 데이터와 DB 데이터를 맞춤
			
			loginMember.setMemberNickname(inputMember.getMemberNickname());
			loginMember.setMemberTel(inputMember.getMemberTel());
			loginMember.setMemberAddress(inputMember.getMemberAddress());
		} else {
			if(result == 0) {
				message = "회원 정보 수정 실패";
			} else {
				message = "이미 사용중인 닉네임입니다";
			}
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:info";
	}
	
	
	/** 비밀번호 변경 화면 이동
	 * @return
	 */
	@GetMapping("changePw")
	public String changePw() {
		return "myPage/myPage-changePw";
	}
	
	
	/** 비밀번호 변경 
	 * @param paramMap : 모든 파라미터 맵으로 저장
	 * @param loginMember : session에 등록된 로그인한 회원 정보
	 * @param ra
	 * @return
	 */
	@PostMapping("changePw")
	public String changePw(@RequestParam Map<String, Object> paramMap,
						   @SessionAttribute("loginMember") Member loginMember,
						   RedirectAttributes ra) {
		
		// 로그인한 회원 번호 얻어오기
		int memberNo = loginMember.getMemberNo();
		
		// 현재 + 새 + 회원번호를 서비스로 전달
		int result = service.changePw(paramMap, memberNo);
		
		String path = null;
		String message = null;
		
		if(result > 0) {
			path = "/myPage/info";
			message = "비밀번호가 변경되었습니다";
		} else {
			path = "changePw";
			message = "현재 비밀번호가 일치하지 않습니다";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
	
	/** 프로필 이미지 변경 화면 이동
	 * @return
	 */
	@GetMapping("profile")
	public String profile() {
		
		
		return "myPage/myPage-profile";
	}
	
	
	/** 회원 탈퇴 화면 이동
	 * @return
	 */
	@GetMapping("secession")
	public String secession() {
		return "myPage/myPage-secession";
	}

	
	/** 회원 탈퇴
	 * @param memberPw : 입력 받은 비밀번호
	 * @param loginMember : 현재 로그인한 회원 정보(session)
	 * @param ra 
	 * @param status : session 완료 용도의 객체
	 * 			-> @SessionAttributes 로 등록된 세션을 완료
	 * @return
	 */
	@PostMapping("secession")
	public String secession(@RequestParam("memberPw") String memberPw,
							@SessionAttribute("loginMember") Member loginMember,
							RedirectAttributes ra,
							SessionStatus status) {
		int memberNo = loginMember.getMemberNo();
		
		
		int result = service.secession(memberNo, memberPw);
		
		String path = null; // 
		String message = null;
		
		if(result > 0) {
			path = "/";
			message = "탈퇴 되었습니다";
			status.setComplete(); // session 완료시킴
		} else {
			path = "secession";
			message = "비밀번호가 일치하지 않습니다";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
	/** 업로드 파일 목록 화면 이동
	 * @return
	 */
	@GetMapping("fileList")
	public String fileList() {
		return "myPage/myPage-fileList";
	}
	
	/** 파일 테스트 화면 이동
	 * @return
	 */
	@GetMapping("fileTest")
	public String fileTest() {
		return "myPage/myPage-fileTest";
	}
	
	// Spring에서 파일 업로드를 처리하는 방법
	
	// - enctype="multipart/form-data" 로 클라이언트 요청을 받으면
	//   (문자, 숫자, 파일 등이 섞여있는 요청)
	//   이를 MultipartResolver(FileConfig)를 이용해서 섞여잇는 파라미터를 분리

	//   문자열, 숫자 -> String
	//   파일 		  -> MultipartFile
	
	/**
	 * @param uploadFile : 업로드한 파일 + 파일에 대한 내용 및 설정 내용
	 * @return
	 */
	@PostMapping("file/test1")
	public String fileUpload1(@RequestParam("uploadFile") MultipartFile uploadFile,
							  RedirectAttributes ra) throws Exception{
		String path = service.fileUpload1(uploadFile);
		
		// 파일이 저장되어 웹에서 접근할 수 있는 경로가 반환 되었을 때
		if(path != null) {
			ra.addFlashAttribute("path", path);
		}
		
		return "redirect:/myPage/fileTest";
	}
	
}