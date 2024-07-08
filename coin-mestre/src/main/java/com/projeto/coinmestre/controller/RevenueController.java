package com.projeto.coinmestre.controller;

import com.projeto.coinmestre.base.PageReq;
import com.projeto.coinmestre.base.PageRes;
import com.projeto.coinmestre.dto.req.RevenueReqDTO;
import com.projeto.coinmestre.dto.res.RevenueResDTO;
import com.projeto.coinmestre.dto.res.RevenueValueResDTO;
import com.projeto.coinmestre.service.RevenueService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/revenues")
@AllArgsConstructor
public class RevenueController {

    private RevenueService service;

    @PreAuthorize("isAuthenticated()")
    // Retorna status code 200ok (por padrão) boa pratica do API REST para todas as chamadas do metodo get retorna status 200
    @GetMapping
    public PageRes<RevenueResDTO> index(PageReq query) {
        return this.service.findAllRevenues(query);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}")
    public RevenueResDTO show(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    @PreAuthorize("isAuthenticated()")
    //  Para os metodos que cria algo dentro do serviço se usa o 204 created(boas praticas do API REST)
    @PostMapping
    public ResponseEntity<RevenueResDTO> insert(@Valid @RequestBody RevenueReqDTO dto, UriComponentsBuilder uriBuilder) {

        RevenueResDTO response = this.service.insert(dto);
        URI uri = uriBuilder.path("/revenues/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PreAuthorize("isAuthenticated()")
    //  Quando não se tem um retorno se usa o 204 no content (boas pratica API REST) PARA QUALQUER COISA QUE NÃO ESPERA RETORNO(VOID)
    @DeleteMapping("{id}")
    public ResponseEntity<?> logicalExclusion(@PathVariable("id")Long id){
        this.service.logicalExclusion(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/restore/{id}")
    public void restoreDeleted(@PathVariable("id") Long id) {
        this.service.restoreDeleted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("{id}")
    public RevenueResDTO update(@PathVariable("id") Long id, @Valid @RequestBody RevenueReqDTO dto) {
        return this.service.update(id, dto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("value-of-revenues")
    public RevenueValueResDTO sumOfRevenues() {
        return this.service.valueOfRevenues();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/revenues-open")
    public RevenueValueResDTO revenuesOpen() {
        return this.service.revenuesOpen();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/revenues-close")
    public RevenueValueResDTO revenuesClosed() {
        return this.service.revenuesClose();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/revenues-overdue")
    public RevenueValueResDTO revenuesOverdue(){
        return this.service.revenuesOverdue();
    }

}
