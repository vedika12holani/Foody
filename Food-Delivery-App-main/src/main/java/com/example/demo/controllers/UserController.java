package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.User;
import com.example.demo.services.UserServices;

@Controller
public class UserController
{
	@Autowired
	private UserServices services;

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("userRegistration", new User()); // matches th:object in HTML
		return "register"; // register.html in templates folder
	}

	// 2. Handle form submission from register.html
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("userRegistration") User user) {
		this.services.addUser(user);
		return "redirect:/login"; // or wherever you want after success
	}


	@PostMapping("/addingUser")
	public String  addUser(@ModelAttribute User user)
	{
		System.out.println(user);
		this.services.addUser(user);
		return "redirect:/admin/services";
	}

	@GetMapping("/updatingUser/{id}")
	public String updateUser(@ModelAttribute User user, @PathVariable("id") int id)
	{
		this.services.updateUser(user, id);
		return "redirect:/admin/services";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id" )int id)
	{
		this.services.deleteUser(id);
		return "redirect:/admin/services";
	}
	


}
