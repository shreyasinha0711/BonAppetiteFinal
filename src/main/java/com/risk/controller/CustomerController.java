
package com.risk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.risk.model.Customer;
import com.risk.service.CustomerService;

@Controller
public class CustomerController {
	
	public static int id;
	@Autowired
	private CustomerService customerService;
	
	boolean isPhoneNumberPresent= false;

	// binding customer model

	@RequestMapping(value = "/customerPhone", method = RequestMethod.GET)
	public String greet(Model model) {
		customerService.deleteAllRows();
		model.addAttribute("customer", new Customer()); 
		return "customerPhone";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ModelAndView display(

			@RequestParam("phone") String strPhone) {

		Customer customerlist = customerService.findCustomerByPhone(strPhone);

		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer");
		
		if(customerlist==null)
		{
			Customer customer = new Customer();
			customer.setPhone(strPhone);
			mav.addObject("customer", customer);
			isPhoneNumberPresent= false;
			
		}
		else
		{
			isPhoneNumberPresent= true;
			mav.addObject("customer", customerlist);
		}
		return mav;

	}

	@RequestMapping(value = "/registerSuccess", method = RequestMethod.POST)
	public String saveCustomerDetails(@Valid @ModelAttribute("customer") Customer customer, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "customer";
		}
		if(isPhoneNumberPresent==false) {
		customerService.saveCustomer(customer);
		}
		id= customer.getCustomerId();
		System.out.println("##################"+id);
		redirectAttributes.addFlashAttribute("id", id);
		return "redirect:/order";
	}
}

// saving the customer data into database and directing page to registerSuccess
