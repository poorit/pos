package com.angel.pos.Controller;

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
		

		// http://openAPI.seoul.go.kr:8088/62656869393632313630/json/SearchInfoBySubwayNameService/1/5/����/
		String originalURL = "http://openAPI.seoul.go.kr:8088";
		String key = "62656869393632313630";
		String type ="json";
		String service = "SearchInfoBySubwayNameService";
		String requestStart = "1";
		String requestEnd = "5";
		
		String apiUrl = originalURL+"/"+key+"/"+type+"/"+service+"/"+requestStart+"/"+requestEnd+"/"+stationName;
		
		model.addAttribute("stationName", stationName);
		
		return "searchStation";
	}

}
