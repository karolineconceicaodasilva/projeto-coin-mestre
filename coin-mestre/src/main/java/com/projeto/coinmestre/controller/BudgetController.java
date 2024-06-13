package com.projeto.coinmestre.controller;

import com.projeto.coinmestre.dto.req.BudgetReqDTO;
import com.projeto.coinmestre.dto.res.BudgetResDTO;
import com.projeto.coinmestre.dto.res.BudgetValueResDTO;
import com.projeto.coinmestre.service.BudgetService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
public class BudgetController {

    private BudgetService service;

    // Retorna status code 200ok (por padrão) boa pratica do API REST para todas as chamadas do metodo get retorna status 200
    @GetMapping
    public List<BudgetResDTO> findAll() {
        return this.service.findAllBudgets();
    }

    @GetMapping("{id}")
    public BudgetResDTO findById(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    //  Para os metodos que cria algo dentro do serviço se usa o 204 created(boas praticas do API REST)
    @PostMapping
    public ResponseEntity<BudgetResDTO> insert(@Valid @RequestBody BudgetReqDTO dto, UriComponentsBuilder uriBuilder) {

        BudgetResDTO response = this.service.insert(dto);
        URI uri = uriBuilder.path("/Budgets/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    //  Quando não se tem um retorno se usa o 204 no content (boas pratica API REST) PARA QUALQUER COISA QUE NÃO ESPERA RETORNO(VOID)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public BudgetResDTO update(@PathVariable("id") Long id, @Valid @RequestBody BudgetReqDTO dto) {
        return this.service.update(id, dto);
    }

    @GetMapping("value-of-budget")
    public BudgetValueResDTO sumOfBudget() {
        return this.service.valueOfBudgets();
    }

    @GetMapping("{initPurchaseDate}/{endPurchaseDate}")
    public List<BudgetResDTO> findAllByPurchaseDate(@PathVariable("initPurchaseDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate initPurchaseDate,
                                                    @PathVariable("endPurchaseDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endPurchaseDate) {
        return this.service.findAllByPurchaseDate(initPurchaseDate, endPurchaseDate);
    }
}
