package com.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.UserModel;
import com.service.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userService;	

	@GetMapping("/")
	public String getUsers(Model model) {
		List<UserModel> allUsers = userService.getAllUsers();
		model.addAttribute("userList", allUsers);
		return "index";
	}

	@GetMapping("/addUser")
	public String addUser(Model model) {
		UserModel user = new UserModel();
		model.addAttribute("user", user);
		return "newUser";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") UserModel request) {
		userService.addUser(request);
		return "redirect:/";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") UserModel request) {
		userService.updateUser(request);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{objectId}")
	public String showFormForUpdate(@PathVariable (value="objectId") ObjectId objectId, Model model) {
		
		Optional<UserModel> user = userService.getUserById(objectId);
		System.out.println(user+"  -- ");
		model.addAttribute("user",user);
		return "updateUser";
	}
	
	@GetMapping("/deleteUser/{objectId}")
	public String deleteUser(@PathVariable (value="objectId") ObjectId objectId, Model model) {
		userService.deleteUser(objectId);
		return "redirect:/";
	}

}
