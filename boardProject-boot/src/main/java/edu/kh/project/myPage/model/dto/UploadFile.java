package edu.kh.project.myPage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @Builder : 빌더 패턴을 이용해 객체 생성 및 초기화를 쉽게 진행
// -> 기본 생성자가 생성 못하기 때문에 @NoArgsConstructor 어노테이션 필요함
// -> MyBatis 조회 결과를 담을 때 기본생성자로 객체를 만들기 때문에

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadFile {

	private int fileNo;
	private String filePath;
	private String fileOriginalName;
	private String fileRename;
	private String fileUploadDate;
	private int memberNo;
	private String memberNickname;
	
}