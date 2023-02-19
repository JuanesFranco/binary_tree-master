package com.binary_tree.binary_tree.application.dto;

import com.binary_tree.binary_tree.controller.dto.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseBinaryTreeDto implements Serializable {

    private Object data;
    private String message;
    private List<ErrorDTO> errors;
}
