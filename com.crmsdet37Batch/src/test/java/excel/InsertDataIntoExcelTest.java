package excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class InsertDataIntoExcelTest {
	
	public static void main(String[] args) throws Exception {
		
		//Fetch the data from properties file(External file)
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Excel.xlsx");
		
		Workbook workbook = WorkbookFactory.create(fis);
		
		Sheet sheet = workbook.getSheet("Sheet1");
		
		Row row = sheet.createRow(2);
		
		Cell cell = row.createCell(1);
		
		//write hello bro in excel
		cell.setCellValue("Hello bro");
		
		Row row1 = sheet.createRow(3);
		
		Cell cell1 = row1.createCell(2);
		
		//write hi in excel
		cell1.setCellValue("Hi");
		
		//insert data into excel
		FileOutputStream fileOutputStream=new FileOutputStream("./src/test/resources/Excel.xlsx");
		workbook.write(fileOutputStream);
	}

}
