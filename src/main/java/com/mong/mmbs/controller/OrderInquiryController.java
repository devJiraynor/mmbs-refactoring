package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.service.OrderInquiryService;

@RestController
@RequestMapping("/api/order")
public class OrderInquiryController {
	
@Autowired OrderInquiryService orderInquiryService;

	@GetMapping("/list")
	public ResponseDto<?> getList(@AuthenticationPrincipal String userId) { 
		return orderInquiryService.getList(userId);
	}

}
