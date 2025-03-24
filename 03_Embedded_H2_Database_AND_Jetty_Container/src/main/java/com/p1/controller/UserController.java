package com.p1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.p1.entity.User;
import com.p1.service.UserServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	private UserServiceImpl service;
	
	@GetMapping("/register")
	public String getRegistration(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String putRegistration(@ModelAttribute @Valid User user,BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "register";
		}else{
			if(service.findByName(user.getName()) == null && service.findByEmail(user.getEmail()) == null){
			service.RegisterUser(user);
			return "redirect:/login";
		}else {
			return "register";
		} }   }
	
	
	@GetMapping("/login")
	public String getLoging() {
		return "login";
	}
	
	@PostMapping("/login")
	public String putLoging(@RequestParam String name,@RequestParam String pwd,HttpSession session, Model model) {
		if(service.loginUser(name, pwd)) {
			session.setAttribute("logingInUser", name);
			return  "redirect:/dashboard";
		}else {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}	
	}
	
	@GetMapping("/dashboard")
	public String showDashboard(HttpSession session, Model model) {
		String username = (String)session.getAttribute("logingInUser");
		if(username == null) {
			return "redirect:/login";
		}
		model.addAttribute("usename", username);
		return "dashboard";
	}
	
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
