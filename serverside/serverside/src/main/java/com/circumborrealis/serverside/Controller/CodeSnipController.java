package com.circumborrealis.serverside.Controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.circumborrealis.serverside.Model.CodeSnip;
import com.circumborrealis.serverside.Repository.ICodeSnipRepository;
import com.circumborrealis.serverside.Repository.IUserRepository;

@Controller
public class CodeSnipController {

	@Autowired
	private ICodeSnipRepository iCodeSnipRepository;
	
	@Autowired
	private IUserRepository iUserRepository;

	// to create a new snip
	@RequestMapping(value="/newsnip", method=RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("CodeSnip", new CodeSnip());
		return "newsnip";
	}
	
	// to see all your snips
	@RequestMapping(value="/yoursnips", method = RequestMethod.GET)
	public String fetchUserSnips(ModelMap model, Principal principal) {
	      String name = principal.getName(); //get logged in username
	      model.addAttribute("codesnips", iCodeSnipRepository.findByOwner(name));
	      System.out.println(model);
	      return "yoursnips";
	}
	
	// DANGER! This can possibly be called by any user and any role
	@RequestMapping(value = "/deletesnip/{id}", method = RequestMethod.GET)
    public String deleteSnip(@PathVariable("id") Long Id, Model model) {
    	iCodeSnipRepository.delete(Id);
        return "redirect:../yoursnips";
    } 
	
	// to get one snip, to be able to edit it.
	@RequestMapping(value="/editsnip/{id}", method=RequestMethod.GET)
	public String getSnip(Model model, @PathVariable("id") Long Id){
		System.out.println("CodeSnipController getSnip saavutettu endpointissa /editsnip");
		model.addAttribute("CodeSnip", iCodeSnipRepository.findOne(Id));
		return "editsnip";
	}
	
	// Tämä ongelma johtui kait siitä että securityn näkökulmasta asiat olivat liian löyhästi rakennettu.
		// public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult)
		// @Valid tarkastaa onko olio säännönmukainen
		// @ModelAttribute on itse olio
		// BindingResult tämän kautta tarkastellaan mitä @Valid sanoo
	// to save a snip
	@RequestMapping(value = "savesnip", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute ("CodeSnip") CodeSnip codesnip, BindingResult bindingResult, Principal principal){
		String name = principal.getName();                //get logged in username
		codesnip.setTime(System.currentTimeMillis());     //set time of save for milliseconds passed since epoch
		codesnip.setOwner(name);                          //set owner for the snip to be saved
		iCodeSnipRepository.save(codesnip);               //save snip with corret owner
		return "newsnip";
	}
}


/*          TÄMÄ TÄÄLLÄ ESIMERKKINÄ
@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	if (!bindingResult.hasErrors()) { // validation errors
		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
    		String pwd = signupForm.getPassword();
	    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
	    	String hashPwd = bc.encode(pwd);

	    	User newUser = new User();
	    	newUser.setPasswordHash(hashPwd);
	    	newUser.setUsername(signupForm.getUsername());
	    	newUser.setRole("USER");
	    	if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
	    		repository.save(newUser);}
	    	else {bindingResult.rejectValue("username", "err.username", "Username already exists");    	
    			  return "signup";}
		}
		else {bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
			  return "signup";}
	}
	else {return "signup";}
	return "redirect:/login";    	
} 
*/




















