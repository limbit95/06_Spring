package edu.kh.demo.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.demo.member.model.dto.Member;
import edu.kh.demo.member.model.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@RequestMapping("/")
	public String loginPage() {
		
		return "main/loginpage";
	}
	
	@PostMapping("login")
	public String loginService(HttpServletRequest req, Model model) {
		try {
			String inputId = req.getParameter("inputId");
			String inputPw = req.getParameter("inputPw");
			
			MemberService memberService = new MemberService();
			
			Member loginMember = memberService.login(inputId, inputPw);
			
			HttpSession session = req.getSession();
			
			if(loginMember != null) {
				model.addAttribute("loginMember", loginMember);
				
				return "main/mainpage";
			} else {
				model.addAttribute("message", "아이디 또는 비밀번호 불일치");
			}
		} catch (Exception e) {
			System.out.println("[로그인 중 예외 발생]");
			e.printStackTrace();
		}
		
		return "main/loginpage";
	}
	
}