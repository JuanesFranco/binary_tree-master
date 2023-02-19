package com.binary_tree.binary_tree.service;

import com.binary_tree.binary_tree.application.dto.ResponseBinaryTreeDto;
import com.binary_tree.binary_tree.controller.dto.ErrorDTO;
import com.binary_tree.binary_tree.exception.BinaryTreeException;
import com.binary_tree.binary_tree.exception.DataNotFoundException;
import com.binary_tree.binary_tree.model.BinaryTree;
import com.binary_tree.binary_tree.model.Boy;
import com.binary_tree.binary_tree.model.Node;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinaryTreeService {
    private BinaryTree binaryTree= new BinaryTree();


    public ResponseEntity<ResponseBinaryTreeDto> addBoy(Boy boy) throws BinaryTreeException
    {
        binaryTree.addNode(boy);
            return new ResponseEntity<>(
                    new ResponseBinaryTreeDto(boy,"Se ha guardado exitosamente",
                            null),HttpStatus.OK);
    }

    public ResponseEntity<ResponseBinaryTreeDto> listBoys(int which) throws DataNotFoundException
    {
        return new ResponseEntity<ResponseBinaryTreeDto>(
          new ResponseBinaryTreeDto(binaryTree.listBoys(which),"Success", null)
                ,HttpStatus.OK
        );
    }

    public ResponseEntity<ResponseBinaryTreeDto> prune() throws DataNotFoundException
    {
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto(binaryTree.prune(),"Success", null)
                ,HttpStatus.OK
        );
    }

    public ResponseEntity<ResponseBinaryTreeDto> getBoysGrade() throws DataNotFoundException
    {
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto(binaryTree.getBoysGrade(),"Success", null)
                ,HttpStatus.OK
        );
    }

    public ResponseEntity<ResponseBinaryTreeDto> getBoysByLevel(int searchLevel) throws DataNotFoundException,BinaryTreeException
    {
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto(binaryTree.getBoysByLevel(searchLevel),"Success", null)
                ,HttpStatus.OK
        );
    }

    public ResponseEntity<ResponseBinaryTreeDto> fillTreeBoys(List<Boy> boys) throws BinaryTreeException
    {
        for(Boy boy:boys)
        {
            binaryTree.addNode(boy);
        }
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto(true,"Success", null)
                ,HttpStatus.OK
        );
    }

    public ResponseEntity<ResponseBinaryTreeDto> deleteBoyByIdentification(int identificatioToDelete) throws DataNotFoundException
    {
        binaryTree.deleteNode(identificatioToDelete);
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto(true,"Success", null)
                ,HttpStatus.OK
        );
    }

    public ResponseEntity<ResponseBinaryTreeDto> createTournament(int totalPlayers) throws BinaryTreeException
    {
        binaryTree.setRoot(null);
        binaryTree.setCount(0);
        int variant=totalPlayers*2;
        totalPlayers= totalPlayers +(totalPlayers-1);
        int level=1;
        int start= variant/2;
        int i=1;
        while(i < totalPlayers)
        {
            int totalHorizontal = (int) Math.pow(2,(level-1));
            int ind = start;
            for(int j=0;j<totalHorizontal;j++)
            {
                binaryTree.addNode(new Boy(ind,"Player "+ind,(byte)5,"Manizales"));
                ind= ind + variant;
                i++;
            }
            variant= variant/2;
            start=start/2;
            level++;
        }

        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto(binaryTree.getRoot(),"Success", null)
                ,HttpStatus.OK
        );
    }


}
