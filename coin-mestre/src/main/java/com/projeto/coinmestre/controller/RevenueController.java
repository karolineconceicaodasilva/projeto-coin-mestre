package com.projeto.coinmestre.controller;

import com.projeto.coinmestre.dto.req.RevenueReqDTO;
import com.projeto.coinmestre.dto.res.RevenueResDTO;
import com.projeto.coinmestre.dto.res.RevenueValueResDTO;
import com.projeto.coinmestre.service.RevenueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/revenues")
@AllArgsConstructor
public class RevenueController {

    private RevenueService service;

    // Retorna status code 200ok (por padrão) boa pratica do API REST para todas as chamadas do metodo get retorna status 200
    @GetMapping
    public List<RevenueResDTO> findAll() {
        return this.service.findAllRevenues();
    }

    @GetMapping("{id}")
    public RevenueResDTO findById(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    //  Para os metodos que cria algo dentro do serviço se usa o 204 created(boas praticas do API REST)
    @PostMapping
    public ResponseEntity<RevenueResDTO> insert(@Valid @RequestBody RevenueReqDTO dto, UriComponentsBuilder uriBuilder) {

        RevenueResDTO response = this.service.insert(dto);
        URI uri = uriBuilder.path("/revenues/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    //  Quando não se tem um retorno se usa o 204 no content (boas pratica API REST) PARA QUALQUER COISA QUE NÃO ESPERA RETORNO(VOID)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public RevenueResDTO update(@PathVariable("id") Long id, @Valid @RequestBody RevenueReqDTO dto) {
        return this.service.update(id, dto);
    }

    @GetMapping("value-of-revenues")
    public RevenueValueResDTO sumOfrevenues() {
        return this.service.valueOfRevenues();
    }

    @GetMapping("revenues-open")
    public RevenueValueResDTO revenuesOpen() {
        return this.service.revenuesOpen();
    }

    @GetMapping("revenues-close")
    public RevenueValueResDTO revenuesClosed() {
        return this.service.revenuesClose();
    }

    @GetMapping("{initPurchaseDate}/{endPurchaseDate}")
    public List<RevenueResDTO> findAllByPurchaseDate(@PathVariable("initPurchaseDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate initPurchaseDate,
                                                     @PathVariable("endPurchaseDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endPurchaseDate) {
        return this.service.findAllByPurchaseDate(initPurchaseDate, endPurchaseDate);
    }
}
