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

	/** Todo 상세 조회 (나)
	 * @param todono
	 * @return todo
	 */
	Todo selectOne(int todono);

	/** Todo 상세 조회 (강사님)
	 * @param todoNo
	 * @return todo
	 */
	Todo todoDetail(int todoNo);

	/** 완료 여부 변경
	 * @param todo
	 * @return result
	 */
	int changeComplete(Todo todo);

	/** 할 일 수정
	 * @param todo
	 * @return result
	 */
	int todoUpdate(Todo todo);

	/** 할 일 삭제
	 * @param todoNo
	 * @return result
	 */
	int todoDelete(int todoNo);
	
}