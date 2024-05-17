package edu.kh.project.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ExcelController {

	@GetMapping("excel")
	public String excel(Model model) {
		
		String test = "";
		
		try {
			FileInputStream file = new FileInputStream(("excel.xlsx")); 
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			for(Row row : sheet) {
				for(Cell cell : row) {
					System.out.println(cell.toString() + "\t");
					test += cell.toString() + "\t";
				}
				System.out.println();
			}
			file.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		} 
		
		model.addAttribute("test", test);
		
		return "excel/test";
	}
	
}