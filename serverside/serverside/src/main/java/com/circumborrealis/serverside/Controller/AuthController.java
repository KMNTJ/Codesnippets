package com.circumborrealis.serverside.Controller;
//AuthController is short for Authentication and Authorisation
//This controller deals with login/logout functionalities

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.circumborrealis.serverside.Repository.ICodeSnipRepository;
import com.circumborrealis.serverside.Repository.IUserRepository;

@Controller
public class AuthController {

	@Autowired
	IUserRepository iUserRespoitory;
	
	@Autowired
	ICodeSnipRepository iCodeSnipRespoitory;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
		return "/login";
	}
	
	/*
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String forwardToNewSnip(){
		return "/newsnip";
	}
	*/
	
	@RequestMapping(value="/login?error")
	public String loginError(){
		return "redirect:/login?error"; //iso virhetodennäköisyys
	}
	
	/*
	@RequestMapping(value="/logout")
	public String logout(){
		return "redirect:/login?logout"; //iso virhetodennäköisyys
	}
	*/
}

/*  // solution proposals for getting current logged in user:

// kokeillaan #1. toimii!
// kokeillaan #2. ei toimi.
// There was an unexpected error (type=Internal Server Error, status=500).
// org.springframework.security.core.userdetails.User cannot be cast to com.circumborrealis.serverside.Model.User
// kokeillaan #3. toimii!

1. SecurityContextHolder + Authentication.getName() ------ TOIMII ------

@RequestMapping(value="/login", method = RequestMethod.GET)
      public String printUser(ModelMap model) {
 
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName(); //get logged in username
		
      model.addAttribute("username", name);
      return "hello";
 
 }
 
// end of 1.

2. SecurityContextHolder + User.getUsername() ------ EI TOIMI ------

@RequestMapping(value="/login", method = RequestMethod.GET)
 public String printUser(ModelMap model) {

     User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     String name = user.getUsername(); //get logged in username
	
     model.addAttribute("username", name);
     return "hello";

 }
 
// end of 2. 

3. UsernamePasswordAuthenticationToken ------ TOIMII ------

@RequestMapping(value="/login", method = RequestMethod.GET)
  public String printWelcome(ModelMap model, Principal principal ) {
 
      String name = principal.getName(); //get logged in username
      model.addAttribute("username", name);
      return "hello";
 
  }










 */













