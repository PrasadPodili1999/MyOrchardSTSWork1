package com.mt.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WebController {
 
//	@GetMapping(value="/index")
//    public String homepage(){
//        return "index";
//    }
	
	@GetMapping(value="/")
	public String home()
	{
		return ("index.html");
	}
	
	@GetMapping(value="/user")
	public String user()
	{
		return ("index.html");
	}
	
	@GetMapping(value="/admin")
	public String admin()
	{
		return ("index.html");
	}
}
