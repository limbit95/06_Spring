package edu.kh.project.excel.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.common.util.Utility;
import edu.kh.project.excel.model.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("excel")
@RequiredArgsConstructor
public class ExcelController {
	
	private final ExcelService service;

	@GetMapping("")
	public String excel() {
		
		return "excel/test";
	}
	
	@PostMapping("upload")
	public String excel(@RequestParam("excel") MultipartFile excel,
						Model model,
						RedirectAttributes ra) throws Exception {

		// 엑셀 파일의 사원 정보를 임시 테이블에 저장 후 성공하면 
		List<Map<String, String>> employeeList = service.saveToTemp(excel);
		
		String message = null;
		
		if(employeeList.isEmpty()) {
			message = "업로드 실패";
		}
		
		ra.addFlashAttribute("employeeList", employeeList);
		ra.addFlashAttribute("message", message);
		
		return "redirect:/excel";
	}
	
	@GetMapping("invite")
	public String inviteEmployee(RedirectAttributes ra) {
		
		
		
		return "redirect:/excel";
	}
	
	
}