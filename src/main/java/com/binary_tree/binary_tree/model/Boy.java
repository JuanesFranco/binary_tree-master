package com.binary_tree.binary_tree.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Boy {
    @Positive
    @NotNull
    private int identification;
    @NotNull
    @NotBlank
    @Size(max=50)
    private String name;
    @Positive
    @NotNull
    private byte age;
    @NotNull
    @NotBlank
    @Size(max=20)
    private String city;

}
