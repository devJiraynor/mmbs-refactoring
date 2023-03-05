package com.mong.mmbs.dto.response.ask;

import java.util.List;

import com.mong.mmbs.entity.AskEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AskGetListResponseDto {
    List<AskEntity> askList;
}
