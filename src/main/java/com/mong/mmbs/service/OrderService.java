package com.mong.mmbs.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.repository.OrderDetailRepository;
import com.mong.mmbs.repository.OrderRepository;
import com.mong.mmbs.repository.ProductRepository;

import com.mong.mmbs.dto.OrderDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.entity.OrderEntity;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.entity.OrderDetailEntity;
@Service
public class OrderService {

  @Autowired OrderRepository orderRepository;
  @Autowired OrderDetailRepository orderDetailRepository;
  @Autowired ProductRepository productRepository;
  
  public ResponseDto<?> orderInsert(OrderDto dto){
    int productId  = dto.getProductId();
    ProductEntity product = null;
    try {
      product = productRepository.findByProductSeq(productId);
      if (product == null) return ResponseDto.setFailed("Does Not Exists Product");
    } catch (Exception exception) {
      return ResponseDto.setFailed("DataBase Error");
    }
    
    String guestPassword  = dto.getOrderGuestPassword();
    String guestPasswordCheck = dto.getOrderGuestPasswordCheck();

    if (guestPassword != null) {
    try {
      if (!guestPassword.equals(guestPasswordCheck))
        return ResponseDto.setFailed("GuestPassword Does not match");
    } catch (Exception exception) {
        return ResponseDto.setFailed("Exception Error");
    }
  }

    OrderEntity order = new OrderEntity(dto, product);
    System.out.println(order.toString());
    try {
      orderRepository.save(order);
    } catch (Exception exception) {
      return ResponseDto.setFailed("DataBase Error");
    }
    OrderDetailEntity orderDetail = new OrderDetailEntity(dto, order, product);
    try {
      orderDetailRepository.save(orderDetail);
    } catch (Exception exception) {
      return ResponseDto.setFailed("DataBase Error");
    }
    return ResponseDto.setSuccess("result", orderDetail);
  }
}