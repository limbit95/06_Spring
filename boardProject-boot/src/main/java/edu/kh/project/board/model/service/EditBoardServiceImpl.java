package edu.kh.project.board.model.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.controller.EditBoardController;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImg;
import edu.kh.project.board.model.exception.BoardInsertException;
import edu.kh.project.board.model.mapper.EditBoardMapper;
import edu.kh.project.common.util.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties")
public class EditBoardServiceImpl implements EditBoardService{

	private final EditBoardMapper mapper;

	@Value("${my.board.web-path}")
	private String webPath;
	
	@Value("${my.board.folder-path}")
	private String folderPath;
	
	// 게시글 작성
	@Override
	public int boardInsert(Board inputBoard, List<MultipartFile> images) throws Exception{
		// 1. 게시글 부분을 먼저
		//    BOARD 테이블에 INSERT 하기
		// -> INSERT 결과로 작성된 게시글 번호(생성된 시퀀스 번호) 반환 받기
		int result = mapper.boardInsert(inputBoard);
		
		// result == INSERT 결과 값(실패 = 0 / 성공 = 1)
		
		// 삽입 실패 시
		if(result == 0) {
			return 0;
		}
		
		// 삽입된 게시글의 번호를 변수로 저장
		// -> mapper.xml 에서 <selectKey> 태그를 이용해서 생성된
		//    boardNo 가 inputBoard 에 저장된 상태 (얕은 복사 개념 이해 필수)
		int boardNo = inputBoard.getBoardNo();
		
		// 2. 업로드된 이미지가 실제로 존재할 경우
		//    업로드된 이미지만 별도로 저장하여
		//    "BOARD_IMG" 테이블에 삽입하는 코드 작성
		
		// 실제 업로드된 이미지의 정보를 모아둘 List 생성
		List<BoardImg> uploadList = new ArrayList<BoardImg>();
		
		// images 리스트에서 하나씩 꺼내어 선택된 파일이 있는 검사
		for(int i = 0; i < images.size(); i++) {
			// 실제로 선택된 파일이 존재하는 경우
			if(!images.get(i).isEmpty()) {
				// 원본명
				String originalName = images.get(i).getOriginalFilename();
				
				// 변경명
				String rename = Utility.fileRename(originalName);
				
				// 모든 값을 저장할 DTO 생성 (BoardImg)
				BoardImg img = BoardImg.builder()
							.imgPath(webPath)
							.imgOriginalName(originalName)
							.imgRename(rename)
							.imgOrder(i)
							.boardNo(boardNo)
							.uploadFile(images.get(i))
							.build();
				
				uploadList.add(img);
				
			}
		}
		
		// 선택한 파일이 없을 경우 (새로 담은 리스트 uploadList 가 완전히 비어있을 경우)
		// 즉 images에 제출된 파일이 없고 위의 for문을 통해서 uploadList에 삽입되는 이미지 정보도
		// 없기 때문에 애초에 게시글 작성 시 넘어오는 파일이 하나도 없다면 uploadList는 비어있는 것
		if(uploadList.isEmpty()) {
			return boardNo;
		}
		
		// 선택한 파일이 존재할 경우
		// -> "BOARD_IMG" 테이블에 INSERT + 서버에 파일 저장
		
		// result == 삽입된 행의 개수 == uploadList.size()
		result = mapper.insertUploadList(uploadList);
		
		// 다중 INSERT 성공 확인 (uploadList에 저장된 값이 모두 정상 삽입 되었나 확인)
		if(result == uploadList.size()) {
			// 서버에 파일 저장 
			for(BoardImg img : uploadList) {
				img.getUploadFile().transferTo(new File(folderPath + img.getImgRename()));
			}
		} else {
			// 부분적으로 삽입 실패 -> 전체 서비스 실패로 판단
			// -> 이전에 삽입된 내용 모두 rollback
			
			// rollback 하는 방법 
			// == RuntimeException 강제 발생 (@Transactional 을 통해서 rollback 수행)
			
			// 사용자 정의 예외 방식 사용
			throw new BoardInsertException("이미지가 정상 삽입되지 않음");
		}
		
		return boardNo;
	}
	
}