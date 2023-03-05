package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.AmountUpdateDto;
import com.mong.mmbs.dto.DeleteAllFromCartDto;
import com.mong.mmbs.dto.DeleteFromCartDto;
import com.mong.mmbs.dto.PutInCartDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	CartService cartService;
	@PostMapping("/cartInsert")//상세 페이지에서 장바구니로 담는거
	public ResponseDto<?> putInCart(@RequestBody PutInCartDto requestBody){
		ResponseDto<?> result = cartService.putInCart(requestBody);
		return result;
	}

	@PostMapping("/showInCart")//디비에서 장바구니로 불러오는거
	public ResponseDto<?> showInCart(@AuthenticationPrincipal String userid){
		ResponseDto<?> result = cartService.showInCart(userid);
		return result;
	}
	@PostMapping("/cartDelete")//장바구니 화면에서 지우는거
	public ResponseDto<?> deleteFromCart(@AuthenticationPrincipal String userId, @RequestBody DeleteFromCartDto requestBody){
		ResponseDto<?> result = cartService.deleteFromCart(userId, requestBody);
		return result;
	}
	@PostMapping("/cartAmountUpdate")//장바구니 화면에서 수량 수정
	public ResponseDto<?> amountUpdate(@RequestBody AmountUpdateDto requestBody){
		ResponseDto<?> result = cartService.amountUpdate(requestBody);
		return result;
	}
	@PostMapping("/cartAllDelete")//장바구니 화면에서 전부 지우는거
	public ResponseDto<?> deleteAllFromCart(@RequestBody DeleteAllFromCartDto requestBody){
		ResponseDto<?> result = cartService.deleteAllFromCart(requestBody);
		return result;
	}
	@PostMapping("/cartAllAmount")//장바구니 화면에서 수량 합산
	public ResponseDto<?> cartAllAmount(@RequestBody DeleteAllFromCartDto requestBody){
		ResponseDto<?> result = cartService.cartAllAmount(requestBody);
		return result;
	}
}
