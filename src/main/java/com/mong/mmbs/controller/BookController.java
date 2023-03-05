package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.service.ProductService;

@RestController
@RequestMapping("/api/book")

public class BookController<BookListServicevice> {
	
	@Autowired ProductService productService;
	
	@GetMapping("/bookList1/{produtAgeCategory}/{productSubAgeCategory}")
	public ResponseDto<?> getProductAgeList(@PathVariable("produtAgeCategory") String productAgeCategory, @PathVariable("productSubAgeCategory") String productSubAgeCategory) {
		return productService.getProductAgeList(productAgeCategory, productSubAgeCategory);
	}
	
	@GetMapping("/bookList2/{produtGenreCategory}/{productSubGenreCategory}")
	public ResponseDto<?> getProductGenreList(@PathVariable("produtGenreCategory") String productGenreCategory, @PathVariable("productSubGenreCategory") String productSubGenreCategory) {
		return productService.getProductGenreList(productGenreCategory, productSubGenreCategory);
	}
	
	@GetMapping("/{productSeq}")
	public ResponseDto<?> getProduct(@PathVariable("productSeq") int productSeq) {
		return productService.getProduct(productSeq);
	}
}