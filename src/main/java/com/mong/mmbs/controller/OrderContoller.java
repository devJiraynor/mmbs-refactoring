package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.OrderDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.service.OrderService;

@RestController
@RequestMapping("/api/pay")
public class OrderContoller {

  @Autowired OrderService orderService;
  
  @PostMapping("/orderInsert")
  public ResponseDto<?> orderInsert(@RequestBody OrderDto requestBody){
    System.out.println(requestBody.toString());
    ResponseDto<?> result = orderService.orderInsert(requestBody);
    return result;
  }
  
}
