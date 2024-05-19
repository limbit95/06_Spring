package edu.kh.project.excel.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

	/** employee 테이블에 사원 정보 저장
	 * @param excel
	 * @return
	 */
	List<Map<String, String>> saveToTemp(MultipartFile excel) throws Exception ;


	
	
}