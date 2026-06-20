package ddt_extra;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetDataFromJsonFile {
	public static void main(String[] args) throws IOException, ParseException {
//		step 1> create jro of the physical file
		FileReader fr = new FileReader("./src/test/resources/commondata.json");

//		step 2> parse jro to java Object
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fr);

//		step 3> downcast(explicit) to JSONOBject because it will work like HashMap
		JSONObject jObj = (JSONObject) obj;

//		step 4> fetch value by passing key in get(key) and convert it to string 
		String BROWSER = jObj.get("bro").toString();

		System.out.println(BROWSER);
	}
}
