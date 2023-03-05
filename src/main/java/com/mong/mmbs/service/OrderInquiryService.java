package com.mong.mmbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.OrderListResponseDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.entity.OrderDetailEntity;
import com.mong.mmbs.entity.OrderEntity;
import com.mong.mmbs.repository.OrderDetailRepository;
import com.mong.mmbs.repository.OrderRepository;
import com.mong.mmbs.repository.ProductRepository;

@Service
public class OrderInquiryService {
	
	@Autowired OrderRepository orderRepository;
	@Autowired OrderDetailRepository orderDetailRepository;
	@Autowired ProductRepository productRepository;

	// 로그인 된 회원의 userId로 Orders테이블에서 주문리스트 반환
	public ResponseDto<?> getList(String userId) {

		List<OrderListResponseDto> result = new ArrayList<OrderListResponseDto>();
		List<OrderEntity> orderList = new ArrayList<OrderEntity>();

		try {
			orderList = orderRepository.findByOrderUserId(userId);
			for ( OrderEntity order : orderList ) {
				List<OrderDetailEntity> detailList = orderDetailRepository.findByOrderNumber(order.getOrderNumber());
				OrderListResponseDto resultItem = new OrderListResponseDto(order, detailList);
				result.add(resultItem);
			}
		} catch(Exception exception){
			return ResponseDto.setFailed("Database Error");
		}
		return ResponseDto.setSuccess("Success", result);
	}

}

