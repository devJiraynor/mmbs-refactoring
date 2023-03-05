package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.UserUpdateDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.UserDeleteDto;

import com.mong.mmbs.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired UserService userService;
	
	@GetMapping("/")
	public ResponseDto<?> getUser(@AuthenticationPrincipal String userId) {
		return userService.getUser(userId);
	}
	
	@PostMapping("/userUpdate")
	public ResponseDto<?> userUpdate(@RequestBody UserUpdateDto dto) {
		return userService.userUpdate(dto);
	}

	@PostMapping("/userDelete")
	public ResponseDto<?> userDelete(@AuthenticationPrincipal String userId, @RequestBody UserDeleteDto dto) {
		return userService.userDelete(userId, dto);
	} 

}
