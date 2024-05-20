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
import edu.kh.project.excel.model.dto.Employee;
import edu.kh.project.excel.model.exception.SaveToEmployeeException;
import edu.kh.project.excel.model.mapper.ExcelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class ExcelServiceImpl implements ExcelService {

	private final ExcelMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
	@Value("${excel.folder-path}")
	private String excelFolderPath;

	// employee 테이블에 사원 정보 저장
	@Override
	public List<Map<String, String>> readExcel(MultipartFile excel) throws Exception {
		
		String fileRename = Utility.fileRename(excel.getOriginalFilename());
		
		excel.transferTo(new File(excelFolderPath + fileRename));
		
		List<Map<String, String>> excelList =  Utility.readExcel(fileRename, excelFolderPath);

		if(excelList.isEmpty()) {
			return null;
		}
		
		return excelList;
	}

	// 사원 계정 정보 조회
	@Override
	public List<Employee> selectEmployeeList() {
		return mapper.selectEmployeeList();
	}

	// 읽어온 엑셀 파일의 사원 정보 DB에 저장(계정 등록)
	@Override
	public int registEmployee(List<Employee> inputEmployeeList) {
		
		
		List<Employee> employeeList = mapper.selectEmployeeList();
		
		for(int i = 0; i < employeeList.size(); i++) {
			if(employeeList.get(i).getEmpNo().equals(inputEmployeeList.get(i).getEmpNo())) {
				return -1;
			}
		}
		
		int result = 0;
		
		for(Employee employee : inputEmployeeList) {
			result = mapper.registEmployee(employee);
			if(result == 0) {
				return 0;
			}
		}
		
		return 1;
	}
	
}