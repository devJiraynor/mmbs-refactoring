package com.mong.mmbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.repository.ProductRepository;

@Service

public class ProductService {
	
	@Autowired ProductRepository productRepository;
	
	//연령
	public ResponseDto<?> getProductAgeList(String productAgeCategory, String productSubAgeCategory) {
		
		List<ProductEntity> productAgeList = null;
	
		if(productSubAgeCategory.equals("0"))
			try {
			productAgeList = productRepository.findByProductAge(productAgeCategory);
			} catch (Exception exception) {
				return ResponseDto.setFailed("error");
			}
		else 
			try {
			productAgeList = productRepository.findByProductAgeAndProductSubAge(productAgeCategory, productSubAgeCategory);
			}catch (Exception exception) {
				return ResponseDto.setFailed("error");
			}
		return ResponseDto.setSuccess("Success", productAgeList);
	}
		
		// 장르별		
		
	public ResponseDto<?> getProductGenreList(String productGenreCategory, String productSubGenreCategory) {
		System.out.println(productGenreCategory);
		
		List<ProductEntity> productGenreList = null; 
		
		if(productSubGenreCategory.equals("0"))
			try {
			productGenreList = productRepository.findByProductGenre(productGenreCategory);
			}catch (Exception exception) {
				return ResponseDto.setFailed("error");
			}
		else 
			try {
			productGenreList = productRepository.findByProductGenreAndProductSubGenre(productGenreCategory, productSubGenreCategory);
			}catch (Exception exception) {
				return ResponseDto.setFailed("error");
			}

			return ResponseDto.setSuccess("Success", productGenreList);
	}

	public ResponseDto<?> getProduct(int productSeq) {
		ProductEntity product = null;
		try {
			product = productRepository.findByProductSeq(productSeq);
		} catch (Exception exception) {
			return ResponseDto.setFailed("failed");
		}
		return ResponseDto.setSuccess("success", product);
	}


	
//	public ResponseDto<?> getproductLikeList(int productLike) {
//		List<ProductEntity> productLikeList = null;
//		if(productLike > productLike) {
//			productLikeList = productRepository.findByProducLike(productLike);
//			
//			}
//		return ResponseDto.setSuccess("success", productLikeList);	
//}
	
}