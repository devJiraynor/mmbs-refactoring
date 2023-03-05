package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.service.UserService;

@RestController
@RequestMapping("/api")
public class PaymentController {
	
@Autowired UserService userService;
	
	@GetMapping("/paymentInfo/{userId}")
	public ResponseDto<?> getUser(@PathVariable("userId")String userId) {
		return userService.getUser(userId);
	}
}
