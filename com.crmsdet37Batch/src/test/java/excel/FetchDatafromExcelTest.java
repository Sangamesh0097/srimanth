package excel;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author hi
 *
 */

public class FetchDatafromExcelTest {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//Fetch the data from properties file(External file)
		FileInputStream fileInputStreaam=new FileInputStream(".\\src\\test\\resources\\Excel.xlsx");
		
		Workbook workbook = WorkbookFactory.create(fileInputStreaam);
		
		Sheet sheet = workbook.getSheet("Sheet1");
		
		Row row = sheet.getRow(1);
		
		Cell cell = row.getCell(0);
		
		//fetch using getnumericcellvalue
		double value = cell.getNumericCellValue();
		
		System.out.println(value);
		
		//fetch using tostring
		String value1 = cell.toString();
		
		System.out.println(value1);
			
   }
}
