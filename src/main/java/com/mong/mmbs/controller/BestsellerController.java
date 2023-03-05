package com.mong.mmbs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.repository.ProductRepository;
import com.mong.mmbs.service.BestsellerService;

@RequestMapping("/api")
@RestController
public class  BestsellerController{

    @Autowired BestsellerService bestsellerService;
    @Autowired ProductRepository productRepository;
    @GetMapping("/best")
    public ResponseDto<?> Bestseller(){
        return bestsellerService.Bestseller();
    }

    @GetMapping("/Image")
    public ResponseDto<?>MainImage(){
        return bestsellerService.MainImage();
    }

}