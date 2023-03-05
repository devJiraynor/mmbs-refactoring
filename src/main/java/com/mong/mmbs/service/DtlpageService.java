package com.mong.mmbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.DtlLikepageDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.repository.CartRepository;
import com.mong.mmbs.repository.ProductRepository;

@Service
public class DtlpageService {

	@Autowired ProductRepository productRepository;
	@Autowired CartRepository cartRepository;
	
	public ResponseDto<?>dtlPage(int productSeq){
		
		ProductEntity product=null;

		try {
			product=productRepository.findByProductSeq(productSeq);
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		return ResponseDto.setSuccess("성공", product);
	}

//	상세페이지 좋아요
	public ResponseDto<?>dtllikePage(DtlLikepageDto dto ) {
		int productSeq = dto.getProductSeq();
		ProductEntity productEntity = null;
		try {
			productEntity = productRepository.findByProductSeq(productSeq);
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		productEntity.setProductLike(productEntity.getProductLike() + 1);

		try {
			productRepository.save(productEntity);
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		return ResponseDto.setSuccess("성공", null);
		
	}
	
}
