package edu.kh.project.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.service.EditBoardService;
import edu.kh.project.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("editBoard")
public class EditBoardController {

	private final EditBoardService service;
	
	/** 게시글 작성 화면 전환(이동)
	 * @param boardCode
	 * @return /board/boardWrite
	 */
	@GetMapping("{boardCode:[0-9]+}/insert")
	public String boardInsert(@PathVariable("boardCode") int boardCode) {
		
		return "board/boardWrite";
	}
	
	/**
	 * @param boardCode : 어떤 게시판에 작성할 글인지 구분하기 위한 요소
	 * @param inputBoard : 입력된 값(게시글 제목과 내용) DTO에 세팅 (커맨드 객체)
	 * @param loginMember : 로그인한 회원 번호 (누가 작성했는지 알기 위해)
	 * @param images : type="file"인 input 태그들이 제출한 데이터들(이미지 파일) 
	 * @param ra : Redirect 시 request scope 로 데이터 전달
	 * @return
	 */
	@PostMapping("{boardCode:[0-9]+}/insert")
	public String boardInsert(@PathVariable("boardCode") int boardCode,
							  @ModelAttribute Board inputBoard,
							  @SessionAttribute("loginMember") Member loginMember,
							  @RequestParam("images") List<MultipartFile> images,
							  RedirectAttributes ra) throws Exception{
		
		// List<MultipartFile> images
		// - 5개 모두 업로드 		=> 0~4번 인덱스에 파일 저장됨
		// - 5개 모두 업로드 X 		=> 0~4번 인덱스에 파일 저장X
		// - 2번 인덱스만 업로드 	=> 2번 인덱스만 파일 저장, 0/1/3/4번 인덱스는 저장 X
		
		log.debug("inputBoard : " + inputBoard);
		log.debug("images : " + images);
		// [문제점]
		// - 파일이 선택되지 않은 input 태그도 제출되고 있음
		//   (제출은 되었지만 데이터는 "" (빈칸))
		
		// -> 파일 선택이 안 된 input 태그 값을 서버에 저장하려고 하면 오류가 발생함
		
		// [해결방법]
		// - 무작정 서버에 저장 X
		// -> 제출된 파일이 있는지 확인하는 로직을 추가 구성해야함
		
		// +List 요소의 index 번호 == IMG_ORDER 와 같음
		
		// 1. boardCode, 로그인한 회원 번호를 inputBoard에 세팅
		inputBoard.setBoardCode(boardCode);
		inputBoard.setMemberNo(loginMember.getMemberNo());
		
		// 2. 서비스 메서드 호출 후 결과 반환 받기
		// -> 성공 시 [상세 조회]를 요청할 수 있도록
		//    삽입된 게시글 번호를 반환 받기
		int boardNo = service.boardInsert(inputBoard, images);
		
		// 3. 서비스 수행 결과에 따라 message, Redirect 경로 지정
		String path = null;
		String message = null;
		
		if(boardNo > 0) {
			path = "/board/" + boardCode + "/" + boardNo; // 상세 조회
			message = "게시글이 작성 되었습니다!";
		} else {
			path = "insert";
			message = "게시글 작성 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
}