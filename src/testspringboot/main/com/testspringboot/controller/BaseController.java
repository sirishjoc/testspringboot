package com.testspringboot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleRuleVoilationException(
			IllegalArgumentException ex, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, String> map = new HashMap<>();
		map.put("error", ex.getMessage());
		return ResponseEntity.badRequest().body(map);// .sendError(HttpServletResponse.SC_BAD_REQUEST,
														// ex.getMessage());
	}
	
}
