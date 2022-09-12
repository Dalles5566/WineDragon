package com.dallas.WineDragonSpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dallas.WineDragonSpringBoot.dao.UserDAO;
import com.dallas.WineDragonSpringBoot.vo.User;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class FrontPageController {

	@Autowired
	private UserDAO userDAO;
	
	
	@GetMapping("/")
	public String getIndexPage() {
		System.out.println("here");
		return "index";
	}

	// it will redirect the users to windlist.jsp 
	@GetMapping("/winelist")
	public String goToWineList() {
		return "winelist";
	}
	

	@GetMapping("/login")
	public String goToLoginPage() {
		return "loginpage";
	}

	@PostMapping("/login")
	public String login(@RequestBody User user, ModelMap model) {
		if (user == null) {
			return "winelist";
		}
		String correctPassword = userDAO.getPasswordByUserName(user.getUsername());
		if (correctPassword == null) {
			return "winelist";
		}
		if (user.getUsername().equals("dallas") && user.getPassword().equals(correctPassword)) {
			model.addAttribute("username", user.getUsername());
			return "winecrud";
		} else {
			return "winelist";
		}

	}

}