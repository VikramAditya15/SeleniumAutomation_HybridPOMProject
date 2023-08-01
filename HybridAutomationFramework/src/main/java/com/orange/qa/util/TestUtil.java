package com.orange.qa.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.orange.qa.base.TestBase;

public class TestUtil extends TestBase{

	public TestUtil() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

	public static Object[][] getJsonData(String testDataPath, Method methodName) throws IOException, ParseException {
		FileReader fr = new FileReader(testDataPath);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(fr);
		JSONArray jsonArray = (JSONArray) jsonObj.get(methodName.getName());
		Object[][] objData = new Object[jsonArray.size()][1];
		HashMap<String, String> dataMap = new HashMap<String, String>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonTestData = (JSONObject) jsonArray.get(i);
			@SuppressWarnings("unchecked")
			Set<String> keys = jsonTestData.keySet();
			Iterator<String> it = keys.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = (String) jsonTestData.get(key);
				dataMap.put(key, value);
			}
			objData[i][0] = dataMap;
		}
		return objData;
	}
	
	public static void takeScreenshot(String methodName) throws IOException
	{
		File srcFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") +"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png"));
		
	}

}
