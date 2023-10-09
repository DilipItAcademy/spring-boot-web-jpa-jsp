package com.tek.teacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tek.teacher.dto.UserReigtserDto;
import com.tek.teacher.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("register")
	public String sayHello() {
		return "register";
	}

	// User Register
	@PostMapping("user/register")
	public ModelAndView registerUser(HttpServletRequest request) {

		// Extracting Data From HttpServletRequest to DTO
		UserReigtserDto userReigtserDto = new UserReigtserDto();
		userReigtserDto.setName(request.getParameter("name"));
		userReigtserDto.setEmailId(request.getParameter("email"));
		userReigtserDto.setContact(request.getParameter("contact"));
		userReigtserDto.setPassword(request.getParameter("pwd"));

		String result = userService.userRegistration(userReigtserDto);

		ModelAndView modelAndView = new ModelAndView();
		// setting result jsp file name
		modelAndView.setViewName("result");
		modelAndView.addObject("message", result);

		return modelAndView;
	}

	// To load the Login Page
	@GetMapping("login")
	public ModelAndView loadLoginPage() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");

		return modelAndView;
	}

	@PostMapping("/loginCheck")
	public ModelAndView validateUser(HttpServletRequest request) {

		String result = userService.validateUser(request.getParameter("email"), request.getParameter("pwd"));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("result");
		modelAndView.addObject("message", result);

		return modelAndView;
	}

}
