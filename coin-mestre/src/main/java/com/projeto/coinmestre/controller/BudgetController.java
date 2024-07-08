package com.projeto.coinmestre.controller;

import com.projeto.coinmestre.base.PageReq;
import com.projeto.coinmestre.base.PageRes;
import com.projeto.coinmestre.dto.req.BudgetReqDTO;
import com.projeto.coinmestre.dto.res.BudgetResDTO;
import com.projeto.coinmestre.service.BudgetService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/budget")
@AllArgsConstructor
public class BudgetController {

    private BudgetService service;

    @PreAuthorize("isAuthenticated()")
    // Retorna status code 200ok (por padrão) boa pratica do API REST para todas as chamadas do metodo get retorna status 200
    @GetMapping
    public PageRes<BudgetResDTO> index(PageReq query) {
        return this.service.findAllBudgets(query);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}")
    public BudgetResDTO show(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    @PreAuthorize("isAuthenticated()")
    //  Para os metodos que cria algo dentro do serviço se usa o 204 created(boas praticas do API REST)
    @PostMapping
    public ResponseEntity<BudgetResDTO> insert(@Valid @RequestBody BudgetReqDTO dto, UriComponentsBuilder uriBuilder) {

        BudgetResDTO response = this.service.insert(dto);
        URI uri = uriBuilder.path("/budgets/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PreAuthorize("isAuthenticated()")
    //  Quando não se tem um retorno se usa o 204 no content (boas pratica API REST) PARA QUALQUER COISA QUE NÃO ESPERA RETORNO(VOID)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
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
    public BudgetResDTO update(@PathVariable("id") Long id, @Valid @RequestBody BudgetReqDTO dto) {
        return this.service.update(id, dto);
    }

}
