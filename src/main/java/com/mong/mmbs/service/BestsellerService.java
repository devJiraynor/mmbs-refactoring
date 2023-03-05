package com.mong.mmbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.repository.ProductRepository;

@Service
public class BestsellerService {
    
    @Autowired ProductRepository productRepository;

    public ResponseDto<?> Bestseller (){
        List<ProductEntity> ProductBast=null;
        try {
            ProductBast= productRepository.findTop10ByOrderByProductLikeDesc();
        } catch (Exception exception) {
            return ResponseDto.setFailed("Database Error");
        }
        
        return ResponseDto.setSuccess("성공", ProductBast);
    }

    public ResponseDto<?> MainImage(){
        List<ProductEntity> BastImage = null;
        try {
            BastImage = productRepository.findAll();
        } catch (Exception exception) {
            return ResponseDto.setFailed("Database Error");
        }
        return ResponseDto.setSuccess("성공", BastImage);
    }

}
