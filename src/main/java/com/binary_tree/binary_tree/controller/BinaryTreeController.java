package com.binary_tree.binary_tree.controller;

import com.binary_tree.binary_tree.application.dto.ResponseBinaryTreeDto;
import com.binary_tree.binary_tree.exception.BinaryTreeException;
import com.binary_tree.binary_tree.exception.DataNotFoundException;
import com.binary_tree.binary_tree.model.Boy;
import com.binary_tree.binary_tree.model.TestDTO;
import com.binary_tree.binary_tree.service.BinaryTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "binarytree")
@Validated
@CrossOrigin("http://localhost:4200")
public class BinaryTreeController {

    @Autowired
    private BinaryTreeService binaryTreeService;

    @PostMapping
    public @ResponseBody
    ResponseEntity<?> addBoy(@Valid @RequestBody Boy boy) throws BinaryTreeException {
        return binaryTreeService.addBoy(boy);
    }

    @GetMapping("/preorden")
    public @ResponseBody
    ResponseEntity<?> listBoysPreOrden() throws DataNotFoundException {
        return binaryTreeService.listBoys(1);
    }

    @GetMapping("/inorden")
public @ResponseBody
ResponseEntity<?> listBoysInOrden() throws DataNotFoundException {
    return binaryTreeService.listBoys(2);
}

    @GetMapping("/postorden")
    public @ResponseBody
    ResponseEntity<?> listBoysPostOrden() throws DataNotFoundException {
        return binaryTreeService.listBoys(3);
    }

    @GetMapping("/prune")
    public @ResponseBody
    ResponseEntity<?> prune() throws DataNotFoundException {
        return binaryTreeService.prune();
    }

    @GetMapping("/boysgrade")
    public @ResponseBody
    ResponseEntity<?> getBoysGrade() throws DataNotFoundException {
        return binaryTreeService.getBoysGrade();
    }

    @GetMapping("/level/{level}")
    public @ResponseBody
    ResponseEntity<?> getBoysByLevel(@PathVariable int level) throws DataNotFoundException,BinaryTreeException {
        return binaryTreeService.getBoysByLevel(level);
    }

    @PostMapping("fill")
    public @ResponseBody ResponseEntity<?> fillBoys(@RequestBody List<Boy> boys) throws BinaryTreeException
    {
        return binaryTreeService.fillTreeBoys(boys);
    }

    @GetMapping("/delete/{identificationToDelete}")
    public @ResponseBody
    ResponseEntity<?> deleteBoyByIdentification(@PathVariable int identificationToDelete) throws DataNotFoundException {
        return binaryTreeService.deleteBoyByIdentification(identificationToDelete);
    }


    @PostMapping("test/{cat1}")
    public @ResponseBody ResponseEntity<ResponseBinaryTreeDto> testCommunication(@PathVariable String cat1)
    {
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto("Hola "+cat1,"Satisfactoria",null), HttpStatus.OK);
    }


    @PostMapping("test2/{id}")
    public @ResponseBody ResponseEntity<ResponseBinaryTreeDto> testReceiveParams(@PathVariable String id,
                                                                                 @RequestBody Boy boy)
    {
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto("Hola "+id + " "+boy.toString(),"Satisfactoria",null), HttpStatus.OK);
    }


    @PostMapping("test3")
    public @ResponseBody ResponseEntity<ResponseBinaryTreeDto> test3ReceiveParams(@Valid
                                                                                 @RequestBody TestDTO test)
    {
        return new ResponseEntity<ResponseBinaryTreeDto>(
                new ResponseBinaryTreeDto("Hola "+test.getId() + " "+test.getBoy().toString(),"Satisfactoria",null), HttpStatus.OK);
    }


    @GetMapping("/createtournament/{totalPlayers}")
    public @ResponseBody
    ResponseEntity<?> createTournament(@PathVariable int totalPlayers) throws BinaryTreeException {
        return binaryTreeService.createTournament(totalPlayers);
    }
}
