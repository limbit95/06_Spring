package edu.kh.project.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.project.board.model.dto.Comment;
import edu.kh.project.board.model.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// fetch - 비동기요청 사용할 때
// "comment" 요청이 오면 해당 Controller 에서 처리하는데
// 그 때마다 @ResponseBody 를 매번 메서드에 추가했었음

// @RestController (REST API 구축을 위해서 사용하는 Controller)
// = @Controller(요청/응답 제어 + bean 등록)
//   + @ResponseBody (응답 본문으로 데이터 자체를 반환)

// - @RestController : 모든 응답을 응답 본문(ajax)으로 반환하는 Controller

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

	private final CommentService service;
	
	/** 댓글 목록 조회
	 * @param boardNo
	 * @return
	 */
	@GetMapping("")
	public List<Comment> select(@RequestParam("boardNo") int boardNo){
		// HttppMessageConverter 가
		// List -> JSON (문자열)로 변환해서 응답해줌
		return service.select(boardNo);
	}
	
	/** 댓글/답글 등록
	 * @return
	 */
	@PostMapping("")
	public int insert(@RequestBody Comment comment) {
		return service.insert(comment);
	}
	
	/** 댓글 삭제
	 * @param commentNo
	 * @return
	 */
	@DeleteMapping("")
	public int delete(@RequestBody int commentNo) {
		return service.delete(commentNo);
	}
	
	/** 댓글 수정
	 * @param comment
	 * @return
	 */
	@PutMapping("")
	public int update(@RequestBody Comment comment) {
		return service.update(comment);
	}
	
}