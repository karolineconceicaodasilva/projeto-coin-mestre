package com.projeto.coinmestre.controller;

import com.projeto.coinmestre.base.PageReq;
import com.projeto.coinmestre.base.PageRes;
import com.projeto.coinmestre.dto.req.UserReqDTO;
import com.projeto.coinmestre.dto.res.UserResDTO;
import com.projeto.coinmestre.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService service;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public PageRes<UserResDTO> index(PageReq query) {
        return this.service.findAll(query);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public UserResDTO show(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<UserResDTO> store(@Valid @RequestBody UserReqDTO dto, UriComponentsBuilder uriBuilder) {
        UserResDTO response = this.service.insert(dto);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public UserResDTO update(@PathVariable("id") Long id, @Valid @RequestBody UserReqDTO dto) {
        return this.service.update(id, dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void logicalExclusion(@PathVariable("id") Long id) {
        this.service.logicalExclusion(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/restore/{id}")
    public void restoreDeleted(@PathVariable("id") Long id) {
        this.service.restoreDeleted(id);
    }

}