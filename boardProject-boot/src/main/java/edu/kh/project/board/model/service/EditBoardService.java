package edu.kh.project.board.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.dto.Board;
import jakarta.mail.Multipart;

public interface EditBoardService {

	/** 게시글 작성
	 * @param inputBoard
	 * @param images
	 * @return boardNo
	 */
	int boardInsert(Board inputBoard, List<MultipartFile> images) throws Exception;

	/** 게시글 수정
	 * @param inputBoard
	 * @param images
	 * @param deleteOrder
	 * @return result
	 */
	int boardUpate(Board inputBoard, List<MultipartFile> images, String deleteOrder) throws IllegalStateException, IOException;
	
	
	
}