package edu.kh.project.common.filter;

import java.io.IOException;

import edu.kh.project.member.model.dto.Member;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// Filter : 요청, 응답 시 걸러내거나 추가할 수 있는 객체
// 1. jakarta.servlet.filter 인터페이스 상속 받기
// 2. doFilter() 메서드 Overriding 해야함

// 로그인이 되어있지 않은 경우 특정 페이지로 돌어가게함
public class LoginFilter implements Filter{

	// 필터 동작을 정의하는 메서드
	@Override
	public void doFilter(ServletRequest request,
						 ServletResponse response, 
						 FilterChain chain)	throws IOException, ServletException {
		
		// ServletRequest : HttpServletRequest의 부모
		// ServletResponse : HttpServletResponse의 부모
		
		// HTTP 통신이 가능한 형태로 다운 캐스팅
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		// Session 얻어오기
		HttpSession session = req.getSession();
		
		// session에서 로그인한 회원 정보를 얻어옴
		// 얻어왔으나, 없을 때 -> 로그인이 되어있지 않는 상태
		if(session.getAttribute("loginMember") == null) {
			// /loginError 재요청
			// resp를 이용해서 원하는 곳으로 리다이렉트
			resp.sendRedirect("/loginError");
		} else {
			// 로그인이 되어있는 경우
			
			// FilterChain
			// - 다음 필터 또는 Dispatcher Servlet과 연결된 객체
			
			// 다음 필터로 요청/응답 객체를 전달
			// (만약 없으면 Dispatcher Servlet으로 전달)
			chain.doFilter(request, response);
		}
		
	}
	
}