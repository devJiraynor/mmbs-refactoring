package com.mong.mmbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.GiftDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.entity.GiftEntity;
import com.mong.mmbs.entity.OrderEntity;
import com.mong.mmbs.repository.GiftRepository;
import com.mong.mmbs.repository.OrderRepository;

import java.util.List;

@Service
public class GiftService {

	@Autowired GiftRepository giftRepository;
	@Autowired OrderRepository orderRepository;

	public ResponseDto<?>gift(){
		List<GiftEntity> result = null;
		try {
			result=giftRepository.findAll();
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		return ResponseDto.setSuccess("성공", result);
	}
	
	public ResponseDto<?>giftorder(GiftDto dto){
		int GiftCode = dto.getOrderGiftCode();
		String orderNumber = dto.getOrderNumber();
		// System.out.println(orderNumber);

		OrderEntity orderEntity = null;
		try {
			orderEntity = orderRepository.findByOrderNumber(orderNumber);
			// if (orderEntity == null) return ResponseDto.setFailed("Does not Exist Order");
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		orderEntity.setOrderGiftCode(GiftCode);

		try {
			orderRepository.save(orderEntity);
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		return ResponseDto.setSuccess("성공", orderEntity);
	}
}
