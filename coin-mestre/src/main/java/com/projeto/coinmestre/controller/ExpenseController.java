package com.projeto.coinmestre.controller;

import com.projeto.coinmestre.dto.req.ExpenseReqDTO;
import com.projeto.coinmestre.dto.res.ExpenseResDTO;
import com.projeto.coinmestre.dto.res.ExpenseValueResDTO;
import com.projeto.coinmestre.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
//para dizer que essa classe é um controller
@RestController
//é uma anotação de nível de classe que define o prefixo de URL para todas as rotas de um controller.
@RequestMapping("/expenses")
//Anotação para usar os contrutores
@AllArgsConstructor
public class ExpenseController {

    private ExpenseService service;

// Retorna status code 200ok (por padrão) boa pratica do API REST para todas as chamadas do metodo get retorna status 200
    @GetMapping
    public List<ExpenseResDTO> findAll(){
        return this.service.findAllExpenses();
    }

    @GetMapping("{id}")
    public ExpenseResDTO findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }
//  Para os metodos que cria algo dentro do serviço se usa o 204 created(boas praticas do API REST)
    @PostMapping
    public ResponseEntity<ExpenseResDTO> insert(@Valid @RequestBody ExpenseReqDTO dto, UriComponentsBuilder uriBuilder){

        ExpenseResDTO response = this.service.insert(dto);
        URI uri = uriBuilder.path("/expenses/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
//  Quando não se tem um retorno se usa o 204 no content (boas pratica API REST) PARA QUALQUER COISA QUE NÃO ESPERA RETORNO(VOID)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id")Long id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ExpenseResDTO update(@PathVariable("id")Long id , @Valid @RequestBody ExpenseReqDTO dto){
        return this.service.update(id , dto);
    }

    @GetMapping("value-of-expenses")
    public ExpenseValueResDTO sumOfExpenses(){
        return this.service.valueOfExpenses();
    }

    @GetMapping("expenses-open")
    public ExpenseValueResDTO expensesOpen(){
        return this.service.expensesOpen();
    }

    @GetMapping("expenses-close")
    public ExpenseValueResDTO expensesClosed(){
        return this.service.expensesClose();
    }

    @GetMapping("{initPurchaseDate}/{endPurchaseDate}")
    public List<ExpenseResDTO> findAllByPurchaseDate(@PathVariable("initPurchaseDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate initPurchaseDate,
                                                     @PathVariable("endPurchaseDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endPurchaseDate){
        return this.service.findAllByPurchaseDate(initPurchaseDate, endPurchaseDate);
    }
}
