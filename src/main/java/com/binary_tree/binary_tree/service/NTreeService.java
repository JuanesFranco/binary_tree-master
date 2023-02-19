package com.binary_tree.binary_tree.service;

import com.binary_tree.binary_tree.application.dto.ResponseBinaryTreeDto;
import com.binary_tree.binary_tree.exception.BinaryTreeException;
import com.binary_tree.binary_tree.exception.DataNotFoundException;
import com.binary_tree.binary_tree.exception.NTreeException;
import com.binary_tree.binary_tree.model.Boy;
import com.binary_tree.binary_tree.model.NTree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NTreeService {
    private NTree nTree= new NTree();

    public ResponseEntity<ResponseBinaryTreeDto> addBoy(Boy boy, int parentIdentification) throws DataNotFoundException, NTreeException
    {
        nTree.add(boy,parentIdentification);
        return new ResponseEntity<>(
                new ResponseBinaryTreeDto(boy,"Se ha guardado exitosamente",
                        null), HttpStatus.OK);
    }

    public ResponseEntity<ResponseBinaryTreeDto> listBoys() throws DataNotFoundException
    {
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto(nTree.getRoot(),"Success", null)
                ,HttpStatus.OK
        );
    }
}
