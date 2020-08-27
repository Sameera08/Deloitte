package com.deloitte.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.org.model.Response;
import com.deloitte.org.service.URLShortenerService;

@RestController
public class Controller {
	@Autowired
	URLShortenerService urlShortenerService;

	@GetMapping("/retriveURL")
	public ResponseEntity<Response> url(@RequestParam("id") Integer id) {
		String url = urlShortenerService.getUrl(id);
		Response response = new Response();
		response.setCode(HttpStatus.ACCEPTED);
		response.setMessage("success");
		response.setResponse(url);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@PostMapping("/url")
	public ResponseEntity<Response> createValidUrl(@RequestBody String url) {
		Integer createdKey = urlShortenerService.createUrl(url);
		Response response = new Response();
		response.setCode(HttpStatus.CREATED);
		response.setMessage("success");
		response.setResponse(String.valueOf(createdKey));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
