package edu.kh.project.excel.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.excel.model.dto.Employee;

@Mapper
public interface ExcelMapper {

	/** 취소 클릭시 테이블에 저장되었던 사원 정보 삭제
	 * @return
	 */
	int deleteEmployee();

	/** 사원 계정 정보 조회
	 * @return
	 */
	List<Employee> selectEmployeeList();

	/** 읽어온 엑셀 파일의 사원 정보 DB에 저장(계정 등록)
	 * @param employeeList
	 * @return
	 */
	int registEmployee(Employee employee);

	
	
}