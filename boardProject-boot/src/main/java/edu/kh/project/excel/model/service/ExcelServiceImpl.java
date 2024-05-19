package edu.kh.project.excel.model.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.common.util.Utility;
import edu.kh.project.excel.model.exception.SaveToEmployeeException;
import edu.kh.project.excel.model.mapper.ExcelMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

	private final ExcelMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
	@Value("${excel.folder-path}")
	private String excelFolderPath;

	// employee 테이블에 사원 정보 저장
	@Override
	public List<Map<String, String>> saveToTemp(MultipartFile excel) throws Exception {
		
		// 기존 임시 테이블의 모든 데이터 삭제
		int deleteResult = mapper.deleteEmployee();
		
		
		String folderPath = "C:/uploadFiles/excel/";
		String fileRename = Utility.fileRename(excel.getOriginalFilename());
		
		excel.transferTo(new File(excelFolderPath + fileRename));
		
		List<Map<String, String>> employeeList =  Utility.excelRead(fileRename, folderPath);
		
		for(Map<String, String> employee : employeeList) {
			
			int result = mapper.saveToTemp(employee);
			
			if(result == 0) {
				throw new SaveToEmployeeException();
			}
		}
		
		return employeeList;
	}
	
}