package com.mong.mmbs.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.request.ask.AskPatchRequestDto;
import com.mong.mmbs.dto.request.ask.AskPostRequestDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.dto.response.ask.AskGetListResponseDto;
import com.mong.mmbs.dto.response.ask.AskPatchResponseDto;
import com.mong.mmbs.dto.response.ask.AskPostResponseDto;
import com.mong.mmbs.repository.AskRepository;
import com.mong.mmbs.entity.AskEntity;

@Service
public class AskService {

  @Autowired AskRepository askRepository;

  public ResponseDto<AskGetListResponseDto> getList(String userId) {

		List<AskEntity> askList = new ArrayList<AskEntity>();

		try {

			askList = askRepository.findByAskWriter(userId);

		} catch(Exception exception){
			return ResponseDto.setFailed("Database Error");
		}

		AskGetListResponseDto data  = new AskGetListResponseDto(askList);
		return ResponseDto.setSuccess("Success", data);

	}

	public ResponseDto<AskPostResponseDto> post(AskPostRequestDto dto){

		AskEntity askEntity = new AskEntity(dto);

		try {

			askRepository.save(askEntity);

		} catch (Exception exception) {
			return ResponseDto.setFailed("Failed");
		}

		AskPostResponseDto data = new AskPostResponseDto(askEntity);
		return ResponseDto.setSuccess("Ask Write Success", data);

	}

	public ResponseDto<?> get(int askId){
		
		AskEntity ask = null;

		try {

			ask = askRepository.findByAskId(askId);

		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}

		return ResponseDto.setSuccess("Success", ask);

	}

	public ResponseDto<AskPatchResponseDto> patch(AskPatchRequestDto dto) {

		AskEntity ask = null;
		int askId = dto.getAskId();

		try {

			ask = askRepository.findByAskId(askId);
			if (ask == null) ResponseDto.setFailed("Does Not Exist User");

			ask.patch(dto);
			askRepository.save(ask);

		} catch (Exception exception) {
			ResponseDto.setFailed("Database Error");
		}

		AskPatchResponseDto data = new AskPatchResponseDto(ask);
		return ResponseDto.setSuccess("Success", data);

	}
	
	public ResponseDto<?> delete (String userId, int askId){
		
		try {
			AskEntity askEntity = askRepository.findByAskId(askId);
			askRepository.delete(askEntity);
		} catch (Exception exception) {
			ResponseDto.setFailed("Failed");
		}
		
		List<AskEntity> list = new ArrayList<AskEntity>();
		try {
			list = askRepository.findByAskWriter(userId);
		} catch (Exception exception) {
			ResponseDto.setFailed("Failed123");

		}
		return ResponseDto.setSuccess("Success", list);
	}

	public ResponseDto<?> find(String userId, String askStatus, int months, String askSort) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = Date.from(Instant.now().minus(months * 30, ChronoUnit.DAYS));
		String askDateTime = simpleDateFormat.format(date);

		List<AskEntity> askList = new ArrayList<AskEntity>();

		try {
			askList = askRepository.findByAskWriterAndAskDatetimeGreaterThanEqualAndAskSortContainsAndAskStatusContainsOrderByAskDatetimeDesc(userId, askDateTime, askSort, askStatus);
			if (askList == null) return ResponseDto.setFailed("Does not Exist Order");
		} catch(Exception exception){
			exception.printStackTrace();
			return ResponseDto.setFailed("Database Error");
		}

		return ResponseDto.setSuccess("Success", askList);
	
	}
}
