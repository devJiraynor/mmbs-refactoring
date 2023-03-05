package com.mong.mmbs.dto.response.ask;

import com.mong.mmbs.entity.AskEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AskPatchResponseDto {
    AskEntity ask;
}
