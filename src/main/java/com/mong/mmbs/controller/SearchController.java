package com.mong.mmbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mong.mmbs.dto.SearchDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.service.SearchService;


@RequestMapping("/api")
@RestController
public class SearchController {

    @Autowired SearchService searchService;

    @PostMapping("/search")
    public ResponseDto<?> search(@RequestBody SearchDto requsetBody) {
        ResponseDto<?> result = searchService.search(requsetBody);
        return result;
    }
    
}
