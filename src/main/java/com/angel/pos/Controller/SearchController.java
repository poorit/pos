package com.angel.pos.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

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
