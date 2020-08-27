package com.deloitte.org.model;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
      HttpStatus code;
      String error;
	public HttpStatus getCode() {
		return code;
	}
	public void setCode(HttpStatus code) {
		this.code = code;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
      
}
