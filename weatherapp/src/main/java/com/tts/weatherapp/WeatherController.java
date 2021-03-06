package com.tts.weatherapp;
//A controller is a class that handles web requests directly. Here, this
//class will be responsible for handling our web page's requests.


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WeatherController {
	
	@Autowired
	WeatherService weatherService;
	//using this for SB auto-instantiation
	
	//this method will handle requests to "/"
	//RequestMapping will handle any type of web request (GET/PUT/POST/
	//PATCH/DELETE)
	//@RequestMapping("/") //we could narrow specificity via (value='/', method=RequestMethod.GET [or RequestMethod.GET, RequestMethod.POST, etc])
						//OR also via @GetMapping(value="/")
	@GetMapping(value="/")
	public String getIndex(Model model) { 
		//a model is an object that holds key:value pairs, 
		//similar to a HashMap, but specifically used to hold data 
		//you want to pass between the Java code and your templating 
		//engine (ie Thymeleaf)
		
//		Response response = weatherService.getForecast("43210");
		Request request = new Request();
		request.setZipCode("44314");
		model.addAttribute("request", request); 
		//this replaces the Thymeleaf template in index.html 
		//with the value entered
		
		return "index";
	}
	
	@PostMapping(value="/")
	public String postIndex(Request request, Model model) {
		Response data = weatherService.getForecast(request.getZipCode());
		model.addAttribute("data", data);
		return "index";
	}
	
	
}
