package com.mong.mmbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.SearchDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.repository.ProductRepository;

@Service
public class SearchService {
    @Autowired ProductRepository productRepository;

    public ResponseDto<?> search(SearchDto dto) {
        String productTitle = dto.getProductTitle();
        List<ProductEntity> postsList =null;
        try {
            postsList= productRepository.findByProductTitleContaining(productTitle);
        } catch (Exception exception) {
            return ResponseDto.setFailed("Database Error");
        }
        return ResponseDto.setSuccess("성공", postsList);
    }
    
}
