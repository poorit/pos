package com.angel.pos.Controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

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
		String originalURL, key, type, service, requestStart, requestEnd;
		String apiUrl, stationInfo = "";
		stationName.replaceAll(" ", "");
		stationName = URLEncoder.encode(stationName,"utf-8");
		
		if(stationName.equals("default")){
			stationInfo = "찾고 싶은 역을 검색 해 주세요.";
		}else{
			originalURL = "http://openAPI.seoul.go.kr:8088"; key = "7a5a5a6e46706f6f36355172424c51";
			type ="json"; service = "SearchInfoBySubwayNameService";
			requestStart = "1"; requestEnd = "5";
			
			apiUrl = originalURL+"/"+key+"/"+type+"/"+service+"/"+requestStart+"/"+requestEnd+"/"+stationName;
			
			stationInfo = parsingJsonDatas(apiUrl);
			
			System.out.println("result :::::::: "+stationInfo);
		}
		
		stationName = URLDecoder.decode(stationName,"utf-8");
		model.addAttribute("stationName", stationName);
		model.addAttribute("stationInfo", stationInfo);
		
		return "searchStation";
	}
	
	// TODO : for work
	public String parsingJsonDatas(String requestURL){
		InputStreamReader isr = null;
		String result = ""; 
		JSONObject fullData, wrapData = null;
		JSONArray rows = null;
		
		try {
			URL url = new URL(requestURL);
			
			// 한글 처리를 위해 InputStreamReader를 UTF-8 인코딩으로 감싼다.
			isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			
			// JSON을 Parsing 한다. 문법오류가 날 경우 Exception 발생, without Exception -> parse 메소드
			fullData = (JSONObject)JSONValue.parseWithException(isr);
			
			try {
				//전체를 감싸고 있는 SearchInfoBySubwayNameService 안에 있는 정보 가져옴
				wrapData = (JSONObject)(fullData.get("SearchInfoBySubwayNameService")); 
				System.out.println(wrapData);
				// wrap 안에 있는 key 값중 row키의 값을 배열화 시킴.
				rows = (JSONArray)wrapData.get("row");
				
				//items 안에 있는 row키값들을 출력
				String codes[] = new String[rows.size()];
				for(int i = 0 ; i < codes.length; i++) {
//					result += ((JSONObject)rows.get(i)).get("FR_CODE")+"";
					codes[i] = ((JSONObject)rows.get(i)).get("FR_CODE")+"";
					result += codes[i]+" / ";
				}
			}catch(NullPointerException ne){
				//전체를 감싸고 있는 RESULT 안에 있는 정보 가져옴
				wrapData = (JSONObject)(fullData.get("RESULT"));
				result = wrapData.get("MESSAGE")+"";
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
