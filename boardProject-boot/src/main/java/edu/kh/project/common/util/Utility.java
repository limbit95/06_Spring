package edu.kh.project.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

// 프로그램 전체적으로 사용될 유용한 기능 모음
public class Utility {
	
	public static int seqNum = 1; // 1 ~ 99999 반복
	
	public static String fileRename(String originalFileName) {
		// ex) 20240417102705_00004.jpg
		
		// SimpleDateFormat : 시간을 원하는 형태의 문자열로 간단히 변경
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		// new java.util.Date() : 현재 시간을 저장한 자바 객체
		String date = sdf.format(new java.util.Date());
		
		// 00000 포맷
		String number = String.format("%05d", seqNum);
		
		seqNum++;
		
		if(seqNum == 100000) {
			seqNum = 1; 
		}
		
		// 확장자
		// "문자열".subString(인덱스)
		// - 문자열을 인덱스로부터 끝까지 잘라낸 결과를 반환
		
		// "문자열".lastIndexOf(".")
		// - 문자열에서 마지막 "."의 인덱스를 반환
		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		return date + "_" + number + ext;
	}
	
	
	
	public static List<Map<String, String>> excelRead(String fileRename, String folderPath) {
		
		List<Map<String, String>> employeeList = new ArrayList<Map<String, String>>();
		List<String> column = new ArrayList<String>();
		
		try {
			FileInputStream file = new FileInputStream(folderPath + fileRename); 
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			
			for(int x = 0; x <= sheet.getLastRowNum(); x++) {
				Map<String, String> employee = new HashMap<String, String>();
				employee.put("empName", null);
				employee.put("empId", null);
				employee.put("empEmail", null);
				employee.put("empNickname", null);
				employee.put("empTel", null);
				employee.put("empBirth", null);
				employee.put("empNo", null);
				
				// 행 값 지웠는데 있는 걸로 인식할 때 첫 번째 셀 값이 null이면 해당 로우 건너뛰기
				if(String.valueOf(sheet.getRow(x).getCell(0)).equals("null")) {
					continue;
				}
				
				for(int i = 0; i < sheet.getRow(x).getLastCellNum(); i++) {
					// 해당 셀의 분류 값을 자동으로 인식해서 넣게끔하려 했지만 일일이 지정해서 넣는게 더 관리하기 편해서 65번~71번줄 처럼 대체 변경함
//					if(x == 0) {
//						column.add(String.valueOf(sheet.getRow(x).getCell(i)));
//					}
					
					if(x > 0) {
						// String 형 19950516 -> 1995-05-16으로 변환하는 코드인데 19950516도 DB TO_DATE 변환으로 삽입 가능해서 주석처리함
//						if(i == 5 && sheet.getRow(x).getCell(i) != null) {
//							String date = String.valueOf(sheet.getRow(x).getCell(i));
//							String year = date.substring(0, 4);
//							String month = date.substring(4, 6);
//							String day = date.substring(6, 8);
//							date = year + "-" + month + "-" + day;
//							employee.put(column.get(i), date);
//						}
						employee.put(column.get(i), String.valueOf(sheet.getRow(x).getCell(i)));
					}
				}
				
				if(x > 0) {
					employeeList.add(employee);
				}
				System.out.println();
			}
			file.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println(employeeList);
		
		return employeeList;
	}
	
}