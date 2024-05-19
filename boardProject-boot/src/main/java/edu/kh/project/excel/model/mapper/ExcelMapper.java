package edu.kh.project.excel.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExcelMapper {

	/** Employee 테이블에 사원 정보 저장
	 * @param employee
	 * @return
	 */
	int saveToTemp(Map<String, String> employee);

	/** 취소 클릭시 테이블에 저장되었던 사원 정보 삭제
	 * @return
	 */
	int deleteEmployee();

	
	
}