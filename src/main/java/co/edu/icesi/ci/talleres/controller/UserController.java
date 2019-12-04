package co.edu.icesi.ci.talleres.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.talleres.delegate.ClientDelegateImpl;
import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;
import co.edu.icesi.ci.talleres.validation.Step1;
import lombok.Data;


@Controller
@Data
public class UserController {
	
	ClientDelegateImpl clientDelegate;

	@Autowired
	public UserController(ClientDelegateImpl clientDel) {
		clientDelegate = clientDel;
		
	}

    @GetMapping("/login")
    public String login() {
        return "customlogin";
    }
    @GetMapping("/logout")
    public String logout() {
        return "customlogin";
    }
    
    
	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", clientDelegate.findAll());
		return "users/index";
	}
	
	@GetMapping("/users/add1")
	public String addUser1(Model model) {
		model.addAttribute("userApp", new UserApp());
		model.addAttribute("types", UserType.values());
		return "users/add-user2";
	}
	
	@PostMapping("/users/add2")
	public String saveUser2(@Validated(Step1.class) UserApp user, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancel"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("types", UserType.values());
				return "users/add-user2";
			} else {
				clientDelegate.save(user);
			}
		return "redirect:/users/";
	}
	
}
