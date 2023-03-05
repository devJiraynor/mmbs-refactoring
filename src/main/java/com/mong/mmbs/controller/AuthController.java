package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mong.mmbs.dto.FindIdDto;
import com.mong.mmbs.dto.FindPasswordDto;
import com.mong.mmbs.dto.MailDto;
import com.mong.mmbs.dto.ResetPasswordDto;
import com.mong.mmbs.dto.SignInDto;
import com.mong.mmbs.dto.SignInResponseDto;
import com.mong.mmbs.dto.SignUpDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.service.AuthService;
import com.mong.mmbs.service.MailService;
import com.mong.mmbs.service.MemberService;
import com.mong.mmbs.service.UserService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
	
	@Autowired AuthService authService;
	@Autowired UserService userUpdateService;
	@Autowired MemberService memberService;
	@Autowired MailService mailService;
	
	@PostMapping("/signUp")
	public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody) {
		ResponseDto<?> result = authService.signUp(requestBody);
		return result;
	}
	@PostMapping("/findId")
	public ResponseDto<?> findId(@RequestBody FindIdDto requestBody){
		ResponseDto<?> result = authService.findId(requestBody);
		return result;
	}
	@PostMapping("/findPassword")
	public ResponseDto<?> findPassword(@RequestBody FindPasswordDto requestBody){
		ResponseDto<?> result = authService.findPassword(requestBody);
		return result;
	}
	@PostMapping("/resetPassword")
	public ResponseDto<?> resetPassword(@RequestBody ResetPasswordDto requestBody){
		ResponseDto<?> result = authService.resetPassword(requestBody);
		return result;
	}
	@PostMapping("/signIn")
	public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody) {
		ResponseDto<SignInResponseDto> result = authService.signIn(requestBody);
		return result;
	}
	
	/** 이메일이 DB에 존재하는지 확인 **/
    @GetMapping("/checkEmail/{userEmail}")
    public boolean checkEmail(@PathVariable("userEmail") String userEmail){

    	log.info("checkEmail 진입");
    	System.out.println(userEmail);
        return memberService.checkEmail(userEmail);
    }
    
    /** 비밀번호 찾기 - 임시 비밀번호 발급 **/
    @GetMapping("/sendPwd/{userEmail}")
    public ResponseDto<?> sendPwdEmail(@PathVariable("userEmail") String userEmail) {

    	log.info("sendPwdEmail 진입");
    	log.info("이메일 : "+ userEmail);

        /** 임시 비밀번호 생성 **/
        String tmpPassword = memberService.getTmpPassword();

        /** 임시 비밀번호 저장 **/
        boolean result = memberService.updatePassword(tmpPassword, userEmail);
        if (!result) return ResponseDto.setFailed("failed");

        /** 메일 생성 & 전송 **/
        MailDto mail = mailService.createMail(tmpPassword, userEmail);
        mailService.sendMail(mail);

        log.info("임시 비밀번호 전송 완료");

        return ResponseDto.setSuccess("success", tmpPassword);
    }
	
}
