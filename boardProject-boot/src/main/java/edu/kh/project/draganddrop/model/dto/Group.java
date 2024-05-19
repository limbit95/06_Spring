package edu.kh.project.draganddrop.model.dto;

import java.util.List;

//import com.project.semi.main.model.dto.Lecture;
//import com.project.semi.main.model.dto.LectureFile;
//import com.project.semi.main.model.dto.LectureInquiry;
//import com.project.semi.main.model.dto.LectureRestnum;
//import com.project.semi.main.model.dto.LectureReview;

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
@Builder // 인스턴스 쉽게 만들게해줌.
public class Group {
	
	    private Long id;
	    private String name;
	    private Long parentId;
	    private List<Group> children; // 자식 그룹 리스트



}
