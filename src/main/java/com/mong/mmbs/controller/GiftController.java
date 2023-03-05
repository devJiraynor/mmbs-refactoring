package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.GiftDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.service.GiftService;

@RequestMapping("/api/auth")
@RestController
public class GiftController {
	@Autowired GiftService giftService;
	// 사은품 불러오기
	@GetMapping("/gift")
	public ResponseDto<?>gift(){
		return giftService.gift();
	}
	
	// 사은품 변경
	@PostMapping("/giftorder")
	public ResponseDto<?>giftorder(@RequestBody GiftDto requsetBody){
		return giftService.giftorder(requsetBody);
	}
	

}
