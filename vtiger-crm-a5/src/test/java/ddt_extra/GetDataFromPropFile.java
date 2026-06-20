package ddt_extra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetDataFromPropFile {
	public static void main(String[] args) throws IOException {
//		Step 1> create java representation object of the physical file
		FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");
		
//		step 2> load all the keys by using load(fis)
		Properties pObj = new Properties();
		pObj.load(fis);
		
//		step 3> get the value by passing key in getProperty(key)
		String BROWSER = pObj.getProperty("bro");
		System.out.println(BROWSER);

		fis.close();
	}
}
