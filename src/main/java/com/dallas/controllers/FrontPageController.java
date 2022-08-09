package com.dallas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dallas.vo.User;
import com.dallas.vo.Entity.WineEntity;

@Controller
public class FrontPageController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndexPage() {
		return "index";
	}

	// it will redirect the users to windlist.jsp 
	@RequestMapping(value = "/winelist", method = RequestMethod.GET)
	public String goToWineList() {
		return "winelist";
	}
	
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goToLoginPage() {
		return "loginpage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("username") User user, ModelMap model) {
		if (user == null) {
			return "winelist";
		}
		
		if (user.getUsername().equals("dallas") && user.getPassword().equals("asdasd")) {
			model.addAttribute("username", user.getUsername());
			return "winecrud";
		} else {
			return "winelist";
		}

	}

}