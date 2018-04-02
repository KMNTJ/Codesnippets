package com.circumborrealis.serverside.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.circumborrealis.serverside.Model.CodeSnip;
import com.circumborrealis.serverside.Model.JsonMessage;
import com.circumborrealis.serverside.Model.User;
import com.circumborrealis.serverside.Repository.ICodeSnipRepository;
import com.circumborrealis.serverside.Repository.IUserRepository;

@RestController
public class RestCodeSnipController {

	@Autowired
	private ICodeSnipRepository iCodeSnipRepository;
	
	@Autowired
	private IUserRepository iUserRepository;

	// this class should be refactored into accepting generic type objects
	@RequestMapping(value = "saveSnippetBROKEN", method = RequestMethod.POST)
    public @ResponseBody JsonMessage save(@RequestBody CodeSnip codeSnip, BindingResult bindingResult) {
		JsonMessage msg = null;
		if (!bindingResult.hasErrors()) { // validation errors
			
			// owner pitää siis hakea  
    		String owner     =  codeSnip.getOwner();
    		String details   =  codeSnip.getDetails();
	    	String code      =  codeSnip.getCode();
	    	String language  =  codeSnip.getLanguage();
	    	Long   saveDate  =  codeSnip.getTime();
	    	User   user      =  iUserRepository.findByUsername(owner);

	    	CodeSnip newSnip = new CodeSnip();

	    	newSnip.setOwner(owner);
	    	newSnip.setDetails(details);
	    	newSnip.setCode(code);
	    	newSnip.setLanguage(language);
	    	newSnip.setTime(saveDate);
	    	newSnip.setUser(user);
    		
	    	iCodeSnipRepository.save(newSnip);
	    	System.out.println("TÄS ON NEWSNIP " + newSnip);
    		
	    	System.out.println(owner);
    		System.out.println(details);
    		System.out.println(code);
    		System.out.println(language);
    		System.out.println(saveDate);
    		System.out.println(user);
    		
    		msg = new JsonMessage(1L, "this is a JsonMessage");
    	} 
		else {
    		return null;
    	}
    	return msg;
    }
}























