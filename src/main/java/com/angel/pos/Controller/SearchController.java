package com.angel.pos.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.angel.pos.Dao.MembersDao;

/**
 * Handles requests for the application home page.
 */
@Controller("SearchController")
public class SearchController {
	@Autowired
	@Qualifier("membersDao")
	private MembersDao membersDao;
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchByStationName(String stationName, Model model){
		logger.info("The client has requested that we find the station : "+stationName);
		
		// http://openAPI.seoul.go.kr:8088/62656869393632313630/json/SearchInfoBySubwayNameService/1/5/목동/
		String originalURL = "http://openAPI.seoul.go.kr:8088";
		String key = "62656869393632313630";
		String type ="json";
		String service = "SearchInfoBySubwayNameService";
		String requestStart = "1";
		String requestEnd = "5";
		
		String apiUrl = originalURL+"/"+key+"/"+type+"/"+service+"/"+requestStart+"/"+requestEnd+"/"+stationName;
		
		String stationInfo = getStationInfo(apiUrl);
		
		System.out.println("result :::::::: "+stationInfo);
		
		model.addAttribute("stationName", stationName);
		model.addAttribute("stationInfo", stationInfo);
		
		URL url;
		try {
			url = new URL(apiUrl);
			// 한글 처리를 위해 InputStreamReader를 UTF-8 인코딩으로 감싼다.

			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			
			// JSON을 Parsing 한다. 문법오류가 날 경우 Exception 발생, without Exception -> parse 메소드
			System.out.println("isr = " + isr);
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);
			System.out.println("object = " + object);
			// 객체
			
			//전체를 감싸고 있는 SearchInfoBySubwayNameService 안에 있는 정보 가져옴
			JSONObject wrap = (JSONObject)(object.get("SearchInfoBySubwayNameService")); 
			
			// wrap 안에 있는 key 값중 row키의 값을 배열화 시킴.
			JSONArray items = (JSONArray)wrap.get("row");
			
			//items 안에 있는 row키값들을 출력
			for(int i = 0 ; i < items.size(); i++) {
			JSONObject obj1 = (JSONObject)items.get(i);
			System.out.println("obj1 = " + obj1);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return "searchStation";
	}
	
	// TODO : for work
	public String getStationInfo(String requestURL){
		try {
			//REST API URL을 읽어들여 결과 출력한다
			URL url = new URL(requestURL);
			
			InputStream is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is,"utf-8");	//인코딩
			BufferedReader reader = new BufferedReader(isr);
			StringBuffer buffer = new StringBuffer();
			String line = null;
			String tmpStr = null;
			while((line = reader.readLine()) != null){
				tmpStr = line.toString();
				tmpStr = tmpStr.replaceAll(" ", "");
				
				if(!tmpStr.equals("")) buffer.append(line).append("\r\n");
			}
			reader.close();
			
			//REST API 결과값
			String result = buffer.toString();
			
			return result;
			
		} catch (IOException e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
}
