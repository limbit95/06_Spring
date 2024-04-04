package edu.kh.todo.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.repository.TodoRepository;

public interface TodoService {

	/** 할 일 목록 + 완료된 할 일 개수 조회
	 * @return
	 */
	Map<String, Object> selectAll();

	/** 할 일 추가
	 * @param todoTitle
	 * @param todoContent
	 * @return result
	 */
	int addTodo(String todoTitle, String todoContent);

	/** 할 일 하나만 조회
	 * @param todono
	 * @return
	 */
	Todo selectOne(int todono);
	
}