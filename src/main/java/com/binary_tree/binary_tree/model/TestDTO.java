package com.binary_tree.binary_tree.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TestDTO {
    @NotNull
    @NotBlank
    private String id;
    @Valid
    @NotNull
    private Boy boy;

}
