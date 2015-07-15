package com.angel.pos.Controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
	public String searchByStationName(String stationName, Model model) throws Exception{
		logger.info("The client has requested that we find the station : "+stationName);
		stationName = stationName.replaceAll(" ", "").replace("역", "");
		stationName = URLEncoder.encode(stationName,"utf-8");
		
		String originalURL, key, type, service, requestStart, requestEnd;
		String apiUrl = "", defaultMsg = "";
		List<String> stationInfo = null;
		
		if(stationName.equals("default")){
			defaultMsg = "찾고 싶은 역을 검색 해 주세요.";
		}else{
			originalURL = "http://openAPI.seoul.go.kr:8088"; key = "7a5a5a6e46706f6f36355172424c51";
			type ="json"; 
			requestStart = "1"; requestEnd = "5";
			
			service = "SearchInfoBySubwayNameService";
			apiUrl = originalURL+"/"+key+"/"+type+"/"+service+"/"+requestStart+"/"+requestEnd+"/"+stationName;
			
			try{
				stationInfo = parsingJsonDatas(apiUrl);
			}catch(NullPointerException e){
				defaultMsg = "역 정보를 찾을 수 없습니다.";
			}
		}
		
		stationName = URLDecoder.decode(stationName,"utf-8");
		model.addAttribute("stationName", stationName);
		
		if(stationInfo==null) model.addAttribute("stationInfo", defaultMsg);
		else model.addAttribute("stationInfo", stationInfo);
		
		return "searchStation";
	}
	
	// TODO : for work
	public List<String> parsingJsonDatas(String requestURL) throws NullPointerException{
		InputStreamReader isr = null;
		List<String> result = new ArrayList<String>(); 
		JSONObject fullData, wrapData = null;
		JSONArray rows = null;
		
		try {
			URL url = new URL(requestURL);
			
			// 한글 처리를 위해 InputStreamReader를 UTF-8 인코딩으로 감싼다.
			isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			
			// JSON을 Parsing 한다. 문법오류가 날 경우 Exception 발생, without Exception -> parse 메소드
			fullData = (JSONObject)JSONValue.parseWithException(isr);
			
			//전체를 감싸고 있는 SearchInfoBySubwayNameService 안에 있는 정보 가져옴
			wrapData = (JSONObject)(fullData.get("SearchInfoBySubwayNameService")); 
			System.out.println(wrapData);
			// wrap 안에 있는 key 값중 row키의 값을 배열화 시킴.
			rows = (JSONArray)wrapData.get("row");
			
			//items 안에 있는 row키값들을 출력
			for(int i = 0 ; i < rows.size(); i++) {
				result.add(i,((JSONObject)rows.get(i)).get("FR_CODE")+"");
			}

			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			try { isr.close(); }catch(Exception e){}
		}
		
		return result;
	}
	
}
