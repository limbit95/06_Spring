package edu.kh.project.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.kh.project.common.interceptor.BoardTypeInterceptor;

// Interceptor 가 어떤 요청을 가로챌지 설정하는 클래스

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	// Interceptor 클래스(BoardTypeInterceptor) Bean 등록
	
	@Bean // 개발자가 만들어서 반환하는 객체를 Bean으로 등록 -> 이후 관리는 Spring Container 가 수행
	public BoardTypeInterceptor boardTypeInterceptor() {
		return new BoardTypeInterceptor();
	}
	
	// 동작할 Interceptor 객체를 추가하는 메서드
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Bean으로 등록된 BoardTypeInterceptor 를 얻어와서 매개변수 전달
		registry.addInterceptor(boardTypeInterceptor())
		.addPathPatterns("/**") // 가로챌 요청 주소를 지정
		// /** : / 이하 모든 요청 주소
		
		// 가로채지 않을 주소를 지정
		.excludePathPatterns("/css/**",
							 "/js/**",
							 "/images/**",
							 "/favicon.ico");
		
	}
	
}