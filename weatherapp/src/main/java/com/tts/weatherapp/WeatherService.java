package com.tts.weatherapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/*What this class will do is to be a utility class that we use to make the
API call.

It's very common when making Spring Boot apps to create "Service" classes
that are, essentially, just utility classes.
*/

//this annotation dictates what the class if for, a utility class, to SB

@Service
public class WeatherService {
	
	@Value("${api_key}")
	private String apiKey;
	
	public Response getForecast(String zipCode) {
//		String url="https://api.openweathermap.org/data/2.5/weather";
//		url += "?zip="+zipCode+"&units=imperial&appid=" +apiKey;
		//adding to the url base with our query specifics
		
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("api.openweathermap.org")
            .path("/data/2.5/weather")
            .queryParam("zip", zipCode)
            .queryParam("units", "imperial")
            .queryParam("appid", apiKey)
            .build();
        
        String url = uriComponents.toUriString();
        System.out.println("-------------------------"+url);
        
        //any time you want to make a REST API call, or any general HTTP
        //request via Sping Boot, you create a RestTemplate
        
        RestTemplate restTemplate = new RestTemplate();
        //We want to do an HTTP GET to get the page:

        //CRUCIAL INFO: LOOK UP JAVA EXCEPTION HANDLING!
        Response response;
        try {
        	response = restTemplate.getForObject(url, Response.class);
        //gets the rest template response as a JSON object
        } catch (HttpClientErrorException e) {
        	response = new Response();
        	response.setName("error");
        }
        
        return response;
	}
}
