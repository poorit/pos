package com.angel.pos.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller("MapController")
public class MapController {		
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String map(Locale locale, String city, String map_result_x, String map_result_y, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		System.out.println("city : " + city);
		System.out.println("x : " + map_result_x);
		System.out.println("y : " + map_result_y);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("map_result_x",map_result_x);
		model.addAttribute("map_result_y",map_result_y);

		return "map";
	}
}
