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
		String originalURL = "http://openAPI.seoul.go.kr:8088", key = "6b6b66475062656836304c6f776657";
		String type ="json", service = "SearchInfoBySubwayNameService";
		String requestStart = "1", requestEnd = "5";
		
		String apiUrl = originalURL+"/"+key+"/"+type+"/"+service+"/"+requestStart+"/"+requestEnd+"/"+stationName;
		
		String stationInfo = parsingJsonDatas(apiUrl);
		
		System.out.println("result :::::::: "+stationInfo);
		
		model.addAttribute("stationName", stationName);
		model.addAttribute("stationInfo", stationInfo);
		
		return "searchStation";
	}
	
	// TODO : for work
	public String parsingJsonDatas(String requestURL){
		String result = "";
		try {
			URL url = new URL(requestURL);
			
			// 한글 처리를 위해 InputStreamReader를 UTF-8 인코딩으로 감싼다.
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			
			// JSON을 Parsing 한다. 문법오류가 날 경우 Exception 발생, without Exception -> parse 메소드
			JSONObject fullData = (JSONObject)JSONValue.parseWithException(isr);
			
			//전체를 감싸고 있는 SearchInfoBySubwayNameService 안에 있는 정보 가져옴
			JSONObject wrapData = (JSONObject)(fullData.get("SearchInfoBySubwayNameService")); 
			
			// wrap 안에 있는 key 값중 row키의 값을 배열화 시킴.
			JSONArray rows = (JSONArray)wrapData.get("row");
			
			//items 안에 있는 row키값들을 출력
			for(int i = 0 ; i < rows.size(); i++) {
				result += "FR_CODE "+i+" = " + ((JSONObject)rows.get(i)).get("FR_CODE") +" / ";
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
