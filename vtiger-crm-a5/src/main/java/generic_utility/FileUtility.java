package generic_utility;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtility {
	public String getDataFromJsonFile(String key) throws IOException, ParseException {

//		step 1> create jro of the physical file
		FileReader fr = new FileReader("./src/test/resources/commondata.json");

//		step 2> parse jro to java Object
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fr);

//		step 3> downcast(explicit) to JSONOBject because it will work like HashMap
		JSONObject jObj = (JSONObject) obj;

//		step 4> fetch value by passing key in get(key) and convert it to string 
		String value = jObj.get(key).toString();
		return value;
	}

	public String getDataFromExcelFile(String sheetName, int rowindex, int cellIndex) throws IOException {
//		step 0>  create java representation object of the physical file
		FileInputStream fis = new FileInputStream("./src/test/resources/testScriptData.xlsx");

//		step 1> get the access of workbook
		Workbook wb = WorkbookFactory.create(fis);

//		step 2> get the access of sheet
		Sheet sh = wb.getSheet(sheetName);

//		step 3> get the access of row
		Row row = sh.getRow(rowindex);

//		step 4> get the access of cell
		Cell cell = row.getCell(cellIndex);

//		step 5> get the value
		String value = cell.getStringCellValue();
		
//		step 6> close the workbook
		wb.close();
//		step 7> close the fis
		fis.close();
		
		return value;
	}
}
