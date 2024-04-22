package edu.kh.project.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

	// BOARD 테이블 컬럼
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriteDate;
	private String boardUpdateDate;
	private int readCount;
	private String boardDelFl;
	private String memberNo;
	private String boardCode;
	
	// MEMBER 테이블 JOIN
	private String memberNickname;
	
	// 목록 조회 시 상관 서브 쿼리 결과
	private int commentCount;
	private int likeCount;
	
	// 게시글 작성자 프로필 이미지
	private String profileImg;
	
	// 게시글 목록 썸네일 이미지
	private String thumbnail;
	
	// --- 추가 예정 ---
	// 특정 게시글의 이미지 목록
	
	// 특정 게시글의 작성된 댓글 목록
	
}