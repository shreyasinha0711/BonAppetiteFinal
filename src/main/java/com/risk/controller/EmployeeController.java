package com.risk.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.risk.model.Employee;
import com.risk.service.EmployeeService;


@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * The controller comes and searches for / first and begins with that
	 */
	
	  @RequestMapping(value="/", method=RequestMethod.GET) 
	  public String login(Model model) {
	  
	  model.addAttribute("login", new Employee());
	  
	  return "Login"; 
	  
	  }
	  
	  /**
		 * This method is to jump to register page
		 */
	
	@RequestMapping(value = "/Login")
	public String register(Model model) {
		
		model.addAttribute("employee", new Employee());

		return "register";
	}
	
	
	/**
	 * This method is to add employee details to db and if there is any error during registration that will be checked here
	 */
	
	@RequestMapping(value = "/success_register", method = RequestMethod.POST)
	public String registerSuccess(@Valid @ModelAttribute("employee") Employee user, BindingResult result,ModelMap model) {

		if (result.hasErrors()) {
			return "register";
		}
	
		boolean isInsertedInDb=employeeService.saveEmployee(user);
		
		if (!isInsertedInDb) {
			
			model.addAttribute("hasMistakes",true);
			return "register";
		}
		
		model.addAttribute("success", "Employee " + user.getName() + " registered successfully");
        return "customerPhone";
	

	}
	
	
	 
	/**
	 * This method is to check whether the employee details is already present.if so allow him to login
	 */
	
		  @RequestMapping("/validate")
		  public String processForm( @ModelAttribute("login") Employee employeeDetails,Map<String,Object> map,Model model,BindingResult result) 
		  
		  {
		  
		  
		  boolean employeeExists=employeeService.checkLogin(employeeDetails.getEmail(),employeeDetails.getPassword());
		  
		  if(employeeExists)
		  
		  {   
			  return "customerPhone"; 
		  }
		  else
		  { 
			  
			model.addAttribute("hasError",true);  
			return "Login"; 
		  
		  }
		  
		  }
		 
	
	

}
