package edu.kh.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

	@RequestMapping("/")
	public String mainPage() {
		return "common/main";
	}
	
	// LoginFilter 동작 -> loginError redirect
	// -> message 만들어서 메인페이지로 redirect
	@RequestMapping("loginError")
	public String loginError(RedirectAttributes ra) {
		ra.addFlashAttribute("message", "로그인 후 이용해주세요");
		
		return "redirect:/";
	}
	
}