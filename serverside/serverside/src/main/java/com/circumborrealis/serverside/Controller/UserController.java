package com.circumborrealis.serverside.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.circumborrealis.serverside.Model.CodeSnip;
import com.circumborrealis.serverside.Model.SignupForm;
import com.circumborrealis.serverside.Model.User;
import com.circumborrealis.serverside.Repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


@Controller
public class UserController {
	@Autowired
    private IUserRepository iUserRepository; 
	
    @RequestMapping(value = "signup")
    public String addStudent(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }	
    @RequestMapping(value = "saveuser", method = RequestMethod.GET)
    public String redirect(Model model){
    	model.addAttribute("signupform", new SignupForm());
    	return "signup";
    }
    
    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	System.out.println("HERE0");
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	List<CodeSnip> codeSnips = new ArrayList<CodeSnip>();
		    	newUser.setCodeSnips(codeSnips);
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (iUserRepository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		iUserRepository.save(newUser);
		    		System.out.println("HERE1");
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			System.out.println("HERE2");
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			System.out.println("HERE3");
    			return "signup";
    		}
    	}
    	else {
    		System.out.println("HERE4");
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    
    
}