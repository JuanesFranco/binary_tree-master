package com.binary_tree.binary_tree.controller;

import com.binary_tree.binary_tree.exception.BinaryTreeException;
import com.binary_tree.binary_tree.exception.DataNotFoundException;
import com.binary_tree.binary_tree.exception.NTreeException;
import com.binary_tree.binary_tree.model.Boy;
import com.binary_tree.binary_tree.service.NTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "ntree")
@Validated
@CrossOrigin("http://localhost:4200")
public class NTreeController {
    @Autowired
    private NTreeService nTreeService;

    @PostMapping("/{parentIdentification}")
    public @ResponseBody
    ResponseEntity<?> addBoy(@Valid @RequestBody Boy boy, @PathVariable int parentIdentification) throws DataNotFoundException, NTreeException {
        return nTreeService.addBoy(boy,parentIdentification);
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<?> listBoys() throws DataNotFoundException {
        return nTreeService.listBoys();
    }
}
