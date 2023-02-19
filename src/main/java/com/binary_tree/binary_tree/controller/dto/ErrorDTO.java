package com.binary_tree.binary_tree.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ErrorDTO {
    @NotNull
    @NotBlank
    private int codError;
    @NotNull @NotBlank
    private String errorMessage;
}
