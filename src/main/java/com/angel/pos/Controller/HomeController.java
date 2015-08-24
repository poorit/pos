package com.angel.pos.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.angel.pos.Dao.RankDao;
import com.angel.pos.Domain.Rank;

/**
 * Handles requests for the application home page.
 */
@Controller("HomeController")
public class HomeController {
	@Autowired
	@Qualifier("rankDao")
	private RankDao rankDao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		List<Rank> list = null;
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );

		
		list = rankDao.rankList();
		model.addAttribute("rankList",list);
		
/*		System.out.println(list.get(0));
		System.out.println(list.get(1));*/
		
		
		
		return "home";
	}
}
