package com.mong.mmbs.dto;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AskDeleteDto {
    @NotBlank
    private int askId;
  }






