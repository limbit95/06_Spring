package edu.kh.project.excel.model.dto;

import java.util.List;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImg;
import edu.kh.project.board.model.dto.Comment;
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
public class Employee {

	private String empNo;
	private String empName;
	private String empId;
	private String empEmail;
	private String empNickname;
	private String empTel;
	private String empBirth;
	
}