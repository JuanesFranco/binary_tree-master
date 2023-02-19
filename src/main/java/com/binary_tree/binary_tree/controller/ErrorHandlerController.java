package com.binary_tree.binary_tree.controller;

import com.binary_tree.binary_tree.application.dto.ResponseBinaryTreeDto;
import com.binary_tree.binary_tree.controller.dto.ErrorDTO;
import com.binary_tree.binary_tree.exception.BinaryTreeException;
import com.binary_tree.binary_tree.exception.DataNotFoundException;
import com.binary_tree.binary_tree.exception.NTreeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandlerController {
    @ExceptionHandler(DataNotFoundException.class)
    protected ResponseEntity<?> handle(DataNotFoundException ex){

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
        ResponseBinaryTreeDto response = new ResponseBinaryTreeDto( null, ex.getMessage(), errors);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BinaryTreeException.class)
    protected ResponseEntity<?> handle(BinaryTreeException ex){

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(new ErrorDTO(HttpStatus.CONFLICT.value(), ex.getMessage()));
        ResponseBinaryTreeDto response = new ResponseBinaryTreeDto( null, ex.getMessage(), errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handle(MethodArgumentNotValidException ex){

        List<ErrorDTO> listErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            listErrors.add(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), fieldName +" "+ errorMessage));
        });
        String message = "Algunos campos son inválidos o faltantes, por favor corrija los errores y vuelva a intentarlo";
        ResponseBinaryTreeDto response = new ResponseBinaryTreeDto( null, message, listErrors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NTreeException.class)
    protected ResponseEntity<?> handle(NTreeException ex){

        List<ErrorDTO> errors = new ArrayList<>();
        errors.add(new ErrorDTO(HttpStatus.CONFLICT.value(), ex.getMessage()));
        ResponseBinaryTreeDto response = new ResponseBinaryTreeDto( null, ex.getMessage(), errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
