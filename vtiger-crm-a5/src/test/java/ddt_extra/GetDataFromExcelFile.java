package ddt_extra;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetDataFromExcelFile {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
//		step 0>  create java representation object of the physical file
		FileInputStream fis = new FileInputStream("./src/test/resources/testScriptData.xlsx");
		
//		step 1> get the access of workbook
		Workbook wb = WorkbookFactory.create(fis);
		
//		step 2> get the access of sheet
		Sheet sh = wb.getSheet("org");
		
//		step 3> get the access of row
		Row row = sh.getRow(6);
		
//		step 4> get the access of cell
		Cell cell = row.getCell(0);
		
//		step 5> get the value
		String value = cell.getStringCellValue();
		System.out.println(value);
		
//		step 6> close the workbook
		wb.close();
		
//		step 7> close the fis
		fis.close();
	}
}
