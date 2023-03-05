package com.mong.mmbs.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.mong.mmbs.dto.request.ask.AskPatchRequestDto;
import com.mong.mmbs.dto.request.ask.AskPostRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ask")
@Entity(name = "ask")
public class AskEntity {
//  문의 번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int askId;
//  작성자 아이디 (참조)
	@NotBlank
	private String askWriter;
//  문의 카테고리
	@NotBlank
	private String askSort;
// 	문의 제목
	@NotBlank
	private String askTitle;
//  문의 내용
	@NotBlank
	private String askContent;
//  문의 날짜
	private String askDatetime;
//  문의 상태 [-1: 삭제, 0: 문의 접수, 1: 답변완료 상태]
	private String askStatus;
//  문의 답변
    private String askReply;

	public AskEntity(AskPostRequestDto dto){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		this.askWriter = dto.getAskWriter();
		this.askSort = dto.getAskSort();
		this.askTitle = dto.getAskTitle();
		this.askContent = dto.getAskContent();
		this.askDatetime = dateFormat.format(new Date());
		this.askStatus = "문의 접수";
	}

	public void patch(AskPatchRequestDto dto) {
		this.askSort = dto.getAskSort();
		this.askTitle = dto.getAskTitle();
		this.askContent = dto.getAskContent();
	}
}
