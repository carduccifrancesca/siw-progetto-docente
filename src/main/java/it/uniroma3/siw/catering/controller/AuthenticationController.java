package it.uniroma3.siw.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.catering.model.Credentials;
import it.uniroma3.siw.catering.model.User;
import it.uniroma3.siw.catering.service.CredentialsService;
import it.uniroma3.siw.catering.service.UserService;
import it.uniroma3.siw.catering.validator.CredentialsValidator;
import it.uniroma3.siw.catering.validator.UserValidator;

@Controller
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		return "loginForm.html";
	}
	
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "registerUser.html";
	}
	
	@PostMapping("/register")
	public String newUser(@Valid @ModelAttribute(value = "credentials") Credentials credentials,
			BindingResult userBindingResult, @Valid @ModelAttribute(value = "user") User user,  
			BindingResult credentialsBindingResult ,Model model) {
		this.userValidator.validate(user, userBindingResult);
		this.credentialsValidator.validate(credentials, credentialsBindingResult);
		if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
			credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            return "registrationSuccessfull.html";
		}
		return "registerUser.html";
	}
	
    @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/home";
        }
        return "home";
    }
    

	@GetMapping("/home")
	public String homePage(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/home";
        }
        return "home";
	}
}
