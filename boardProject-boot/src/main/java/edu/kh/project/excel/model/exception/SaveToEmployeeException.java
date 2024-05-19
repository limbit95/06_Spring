package edu.kh.project.excel.model.exception;

public class SaveToEmployeeException extends RuntimeException {
	
	public SaveToEmployeeException() {
		super("사원 정보 저장 중 예외발생");
	}
	
	public SaveToEmployeeException(String message) {
		super(message);
	}
	
}