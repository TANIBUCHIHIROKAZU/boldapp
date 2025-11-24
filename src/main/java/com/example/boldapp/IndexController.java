package com.example.boldapp;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	/**
	 * 
	 * TOPP-001画面 初期表示
	 * param authentication
	 * 
	 **/
	
	@GetMapping("/")
    public String index(Authentication authentication) {
		
        return "topp/index";  // templates/topp/index.html を返す
    }
    
	
	/**
	 * 
	 * LGIN-001画面 初期表示
	 * param authentication
	 * 
	 **/
	
    @GetMapping("/login")
    public String showLoginForm(Authentication authentication) {
    	// ② ログイン済みの場合 → TOPP-001 を表示
    	if (authentication != null 
    		    && authentication.isAuthenticated() 
    		    && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:topp/index";
        }
    	
    	
        return "lgin/login";
    }
    
}
