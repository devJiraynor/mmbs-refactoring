package com.mong.mmbs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mong.mmbs.dto.AmountUpdateDto;
import com.mong.mmbs.dto.DeleteAllFromCartDto;
import com.mong.mmbs.dto.DeleteFromCartDto;
import com.mong.mmbs.dto.PutInCartDto;
import com.mong.mmbs.dto.response.ResponseDto;
import com.mong.mmbs.entity.CartEntity;
import com.mong.mmbs.entity.ProductEntity;
import com.mong.mmbs.repository.CartRepository;
import com.mong.mmbs.repository.ProductRepository;
import com.mong.mmbs.util.ResponseMessage;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductRepository productRepository;

	public ResponseDto<?> showInCart(String userId) {
		List<CartEntity> cartEntity = cartRepository.findByCartUserId(userId);
		if (cartEntity == null)
			return ResponseDto.setFailed("장바구니에 담긴 상품이 없습니다.");
		return ResponseDto.setSuccess(ResponseMessage.SUCCESS, cartEntity);
	}

	// 장바구니 담기

	public ResponseDto<?> putInCart(PutInCartDto dto) {
		String cartUserId = dto.getCartUserId();
		int cartProductId = dto.getCartProductId();
		int cartProductAmount = dto.getCartProductAmount();

		ProductEntity productEntity = null;
		CartEntity cartEntity = null;
		
		List<CartEntity> cartList = new ArrayList<CartEntity>();

		try {

			productEntity = productRepository.findByProductSeq(cartProductId);
			if (productEntity == null) return ResponseDto.setFailed("실패");

			cartEntity = cartRepository.findByCartUserIdAndCartProductId(cartUserId, cartProductId);

			// 해당 회원의 장바구니 레코드가 존재하지 않을 때
			if (cartEntity == null) {
				
				cartEntity = new CartEntity(cartUserId, cartProductAmount, productEntity);
				cartRepository.save(cartEntity);
	
			} 
			// 해당 회원의 장바구니 레코드가 존재할 때
			else {

				cartEntity.setCartProductAmount(cartProductAmount);
				cartRepository.save(cartEntity);

			}

			cartList = cartRepository.findByCartUserId(cartUserId);

		} catch (Exception exception) {
			return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
		}

		return ResponseDto.setSuccess("성공", cartList);
	}

	public ResponseDto<?> deleteFromCart(String userId, DeleteFromCartDto dto) {

		int cartId = dto.getCartId();
		CartEntity cartEntity = null;
		try {
			cartEntity = cartRepository.findByCartId(cartId);
			if (cartEntity == null) return ResponseDto.setFailed("Does not Exist Cart");
			cartRepository.delete(cartEntity);
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		
		List<CartEntity> cartList = new ArrayList<CartEntity>();
		
		try {
			cartList = cartRepository.findByCartUserId(userId);
		} catch (Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		
		return ResponseDto.setSuccess("장바구니에서 삭제되었습니다 .", cartList);
	}

	public ResponseDto<?> amountUpdate(AmountUpdateDto dto) {
		List<CartEntity>cartEntity = dto.getSelectCartList();
		cartRepository.saveAll(cartEntity);
		return ResponseDto.setSuccess("장바구니에서 수정됬었습니다 .", cartEntity);
	}

	public ResponseDto<?> deleteAllFromCart(DeleteAllFromCartDto dto) {

		String cartUserId = dto.getCartUserId();

		List<CartEntity> cartEntity = cartRepository.findByCartUserId(cartUserId);
		if (cartEntity != null)
			cartRepository.deleteAll(cartEntity);
		return ResponseDto.setSuccess("장바구니에서 전부 삭제되었습니다 .", null);
	}

	public ResponseDto<?> cartAllAmount(DeleteAllFromCartDto dto) {

		String cartUserId = dto.getCartUserId();

		List<CartEntity> cartEntity = cartRepository.findByCartUserId(cartUserId);
		int total = 0;
		if (cartEntity != null) {
			for (CartEntity cartEntity2 : cartEntity) {
				total += cartEntity2.getCartProductAmount();
			}
		}

		return ResponseDto.setSuccess("장바구니에 담긴 책의 총수량", total);
	}


}
