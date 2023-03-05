package com.mong.mmbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.UserDeleteDto;
import com.mong.mmbs.dto.UserUpdateDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.entity.UserEntity;
import com.mong.mmbs.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired UserRepository userRepository;
	
    public ResponseDto<?> getUser(String userId) {
    	UserEntity user = null;
    	try {
    		user = userRepository.findByUserId(userId);
    	} catch (Exception exception) {
    		return ResponseDto.setFailed("Database Error");
    	}
    	
    	if (user != null) user.setUserPassword("");
    	
    	return ResponseDto.setSuccess("result", user);
    }
    
	public ResponseDto<?> userUpdate(UserUpdateDto dto){
		
		UserEntity user = null;
		
		try {
			user = userRepository.findByUserId(dto.getUserId());
			if (user == null) ResponseDto.setFailed("Does Not Exist User");
		} catch (Exception exception) {
			ResponseDto.setFailed("Failed");
		}
		
		user.setUpdateUser(dto);
		
		try {
			userRepository.save(user);
		} catch (Exception exception) {
			ResponseDto.setFailed("Failed");
		}
		
		return ResponseDto.setSuccess("Sucess", user);
	}

	public ResponseDto<?> userDelete (String userId, UserDeleteDto dto) {
		
		userId = dto.getUserId();
		String userEmail = dto.getUserEmail();

		UserEntity userEntity = null; 

		try {
			if(!userRepository.existsByUserIdAndUserEmail(userId, userEmail))
				return ResponseDto.setFailed("UserId Or UserEmail Does Not Exist");
				
			userEntity = userRepository.findByUserId(userId);
			userRepository.delete(userEntity);
		} catch (Exception exception) {
			exception.printStackTrace();
			return ResponseDto.setFailed("Failed");
		}
		return ResponseDto.setSuccess("Success", null);
	}
}
