package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.MailDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.service.MailService;
import com.mong.mmbs.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/searchEmail")
@Slf4j
public class MemberController {

	@Autowired MemberService memberService;
	@Autowired MailService mailService;
	
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
        memberService.updatePassword(tmpPassword, userEmail);

        /** 메일 생성 & 전송 **/
        MailDto mail = mailService.createMail(tmpPassword, userEmail);
        mailService.sendMail(mail);

        log.info("임시 비밀번호 전송 완료");

        return ResponseDto.setSuccess("success", true);
    }
    
}
