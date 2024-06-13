package com.projeto.coinmestre.controller;

import com.projeto.coinmestre.dto.req.UserReqDTO;
import com.projeto.coinmestre.dto.res.UserResDTO;
import com.projeto.coinmestre.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService service;


    @GetMapping
    public List<UserResDTO> getAll(){
        return this.service.findAllUsers();
    }

    @GetMapping("{id}")
    public UserResDTO findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PostMapping
    public ResponseEntity<UserResDTO> insert(@Valid @RequestBody UserReqDTO dto, UriComponentsBuilder uriBuilder){
        UserResDTO response = this.service.insert(dto);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")Long id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public UserResDTO update(@PathVariable("id")Long id , @Valid @RequestBody UserReqDTO dto){
        return this.service.update(id , dto);
    }
}