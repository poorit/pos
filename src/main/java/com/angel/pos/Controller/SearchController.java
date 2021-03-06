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
		logger.info("The client has requested that find the station : "+stationName);
		
		// stationName 변환
		if(stationName.equals("")) stationName = "default";
		if(stationName.endsWith("역")) stationName = stationName.replaceAll("역", "");
		stationName = stationName.replaceAll(" ", "");
		stationName = URLEncoder.encode(stationName,"utf-8");
		
		String originalURL, key, type, service, requestStart, requestEnd;
		String apiUrl = "", defaultMsg = "", stationCode = "";
		List<String> stationInfo = null;
		
		if(stationName.equals("default")){
			defaultMsg = "찾고 싶은 역을 검색 해 주세요.";
		}else{
			originalURL = "http://openAPI.seoul.go.kr:8088"; key = "7a5a5a6e46706f6f36355172424c51";
			type ="json"; requestStart = "1"; requestEnd = "5";
			
			try{
				service = "SearchInfoBySubwayNameService";
				apiUrl = originalURL+"/"+key+"/"+type+"/"+service+"/"+requestStart+"/"+requestEnd+"/"+stationName;
				// 역 코드 반환
				stationCode = getStationCodesBySubwayName(apiUrl, stationName);
				
				service = "SearchFacilityByIDService"; requestEnd = "1000";
				apiUrl = originalURL+"/"+key+"/"+type+"/"+service+"/"+requestStart+"/"+requestEnd+"/"+stationCode;
				// 역 정보 반환
				stationInfo = getStationInfoByStationCode(apiUrl); 
			}catch(NullPointerException e){
				if(stationCode==null) defaultMsg = "역 정보를 찾을 수 없습니다.";
				else defaultMsg = "역 주변정보가 없습니다.";
			}
		}
		
		stationName = URLDecoder.decode(stationName,"utf-8");
		model.addAttribute("stationName", stationName);
		
		if(stationCode.equals("") || stationInfo==null) model.addAttribute("stationInfo", defaultMsg);
		else model.addAttribute("stationInfo", stationInfo);
		
		return "searchStation";
	}
	
	// TODO : for work
	public String getStationCodesBySubwayName(String requestURL, String stationName) throws NullPointerException{
		InputStreamReader isr = null;
		JSONObject fullData, wrapData = null;
		JSONArray rows = null;
		String stationCode = "";
		
		try {
			URL url = new URL(requestURL);
			
			// 한글 처리를 위해 InputStreamReader를 UTF-8 인코딩으로 감싼다.
			isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			
			// JSON을 Parsing 한다. 문법오류가 날 경우 Exception 발생, without Exception -> parse 메소드
			fullData = (JSONObject)JSONValue.parseWithException(isr);
			
			//전체를 감싸고 있는 SearchInfoBySubwayNameService 안에 있는 정보 가져옴
			wrapData = (JSONObject)(fullData.get("SearchInfoBySubwayNameService")); 
			// wrap 안에 있는 key 값중 row키의 값을 배열화 시킴.
			rows = (JSONArray)wrapData.get("row");
			
			//items 안에 있는 row키값
			stationName = URLDecoder.decode(stationName,"utf-8");
			if(stationName.equals("까치산") || stationName.equals("신도림")){
				stationCode = ((JSONObject)rows.get(1)).get("STATION_CD")+"";
			}else{
				stationCode = ((JSONObject)rows.get(0)).get("STATION_CD")+"";
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
		
		return stationCode;
	}
	
	public List<String> getStationInfoByStationCode(String requestURL) throws NullPointerException{
		InputStreamReader isr = null;
		JSONObject fullData, wrapData = null;
		JSONArray rows = null;
		List<String> stationInfo = new ArrayList<String>();
		
		try {
			URL url = new URL(requestURL);
			isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			
			fullData = (JSONObject)JSONValue.parseWithException(isr);
			wrapData = (JSONObject)(fullData.get("SearchFacilityByIDService")); 
			rows = (JSONArray)wrapData.get("row");
			
			for(int i = 0 ; i < rows.size(); i++) {
				stationInfo.add(i,((JSONObject)rows.get(i)).get("AREA_NM")+"");
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
		
		return stationInfo;
	}
	
}
