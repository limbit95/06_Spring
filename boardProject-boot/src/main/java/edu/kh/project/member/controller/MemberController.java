package edu.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

// @SessionAttributes( {"key", "key", "key", ...} )
// - Model에 추가된 속성 중
//   key 값이 일치하는 속성을 session scope로 변경

@SessionAttributes({"loginMember"})
@Controller
@Slf4j
@RequestMapping("member")
public class MemberController {

	@Autowired
	private MemberService service;
	
	// [로그인]
	// - 특정 사이트에 아이디/비밀번호 등을 입력해서
	//   해당 정보가 있으면 조회/서비스 이용
	
	// - 로그인 한 정보를 session에 기록하여
	//   로그아웃 또는 브라우저 종료 시 까지
	//   해당 정보를 계속 이용할 수 있게 함
	
	/** 로그인
	 * @param inputMember : 커맨드 객체 (@ModelAttribute 생략)
	 * 						(memberEmail, memberPw 세팅된 상태)
	 * @param ra : redirect 시 request scope로 데이터로 전달하는 객체
	 * @param model : 데이터 전달용 객체(기본 request scope)
	 * @return "redirect:/"
	 */
	@PostMapping("login")
	
	public String login(Member inputMember, // @ModelAttribute 커맨드 객체라고도 함
						RedirectAttributes ra,
						Model model,
						@RequestParam(value="saveId", required = false) String saveId,
						HttpServletResponse resp
						) { 
		
		// 체크박스(saveId => main.html에서 아이디 저장 체크박스에서 체크 했을 때와 안 했을 때의 요소)
		// - 체크가 된 경우 : "on"
		// - 체크가 안 된 경우 : null
		
		// 로그인 서비스 호출
		Member loginMember = service.login(inputMember);
		
		if(loginMember == null) {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다");
		} 
		
		if(loginMember != null){
			// Session scope에 loginMember 추가 
			model.addAttribute("loginMember", loginMember);
			// 1단계 : request scope에 세팅됨
			
			// 2단계 : Class 위에 @SessionAttributes() 어노테이션 때문에
			// 	       session scope로 이동됨
			
			// *********************************************************
			// 아이디 저장(Cookie)
			
			// 쿠키 객체 생성 (Key : Value)
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			// saveId=user01@kh.or.kr
			
			// 클라이언트가 어떤 요청을 할 때 쿠키가 첨부될지 지정
			
			// ex) "/" : IP 또는 도메인 또는 localhost
			//			 뒤에 "/"  ->  메인 페이지 + 그 하위 주소 모두
			cookie.setPath("/");
			
			// 만료 기간 지정
			if(saveId != null) { // 아이디 저장 체크 시
				cookie.setMaxAge(60*60*24*30); // 초 단위로 지정
			} else { // 미 체크 시
				cookie.setMaxAge(0); // 0초 (클라이언트 쿠키 삭제) => 기존 지정해놓은 만료 기간 무효화
			}
			
			// 응답 객체에 쿠키 추가 -> 클라이언트로 전달
			resp.addCookie(cookie);
			// *********************************************************
		}
		
		return "redirect:/";
	}
	

	/** 로그아웃 : Session에 저장된 로그인된 회원 정보 제거(session을 만료, 무효화)
	 * @param SessionStatus : session 을 완료(없앰) 시키는 역할의 객체
	 * 					      - @SessionAttributes 로 등록된 session 만료 
	 * 						  - 서버에서 기존 세션 객체가 사라짐과 동시에
	 * 						  - 새로운 세션 객체가 생성되어 클라이언트와 연결
	 * @return redirect:/
	 */
	@GetMapping("logout")
	public String logout(SessionStatus status) {
		status.setComplete(); // session을 완료 시킴(없앰)
		
		return "redirect:/";
	}
	
	/** 회원 가입 페이지 이동
	 * @return
	 */
	@GetMapping("signup")
	public String signUpPage() {
		return "member/signup";
	}
	
	@ResponseBody // 응답 본문(요청한 fetch) 돌려보내주기
	@GetMapping("checkEmail")
	public int checkEmail(@RequestParam("memberEmail") String memberEmail) {
		return service.checkEmail(memberEmail);
	}
	
}