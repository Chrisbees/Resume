package com.example.demo;

import javax.xml.bind.ValidationException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
	
	private EmailCfg emailCfg;
	
	ModelUser modelUser = new ModelUser();
	
	public Home(EmailCfg emailCfg) {
		this.emailCfg = emailCfg;
	}
	
	@RequestMapping("/resume")
	public String home(Model theModel) {		
		theModel.addAttribute("message", modelUser);
		return "resume-index";
	}
	
	@PostMapping("/resume/message")
	    public String getCategoriesAdd(@RequestBody @ModelAttribute("message") ModelUser modelUser, BindingResult bindingResult) throws ValidationException{
	    	if(bindingResult.hasErrors()) {
	    		throw new ValidationException("Feedback is not vaid");
	    	}
	    	
	    	//Create Mail Sender
	    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    	mailSender.setHost(this.emailCfg.getHost());
	    	mailSender.setPort(this.emailCfg.getPort());
	    	mailSender.setUsername(this.emailCfg.getUsername());
	    	mailSender.setPassword(this.emailCfg.getPassword());
	    	
	    	//Create Email Instance
	    	SimpleMailMessage mailMessage = new SimpleMailMessage();
	    	mailMessage.setFrom(modelUser.getEmail());
	    	mailMessage.setTo("chrisbees@feedback.com");
	    	mailMessage.setSubject("New feedback from " + modelUser.getName());
	    	mailMessage.setText(modelUser.getFeedback());
	    	
	    	//Send email
	    	mailSender.send(mailMessage);
	    	
	        return "resume-index";
	    }
	
}
