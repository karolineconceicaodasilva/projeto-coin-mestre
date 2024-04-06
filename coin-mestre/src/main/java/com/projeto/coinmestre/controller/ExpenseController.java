package com.projeto.coinmestre.controller;

import com.projeto.coinmestre.dto.req.ExpenseReqDTO;
import com.projeto.coinmestre.dto.res.ExpenseResDTO;
import com.projeto.coinmestre.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
public class ExpenseController {

    private ExpenseService service;

    @GetMapping
    public List<ExpenseResDTO> findAll(){
        return this.service.findAllExpenses();
    }

    @GetMapping("{id}")
    public ExpenseResDTO findById(@PathVariable("id") Long id){
        return this.service.findById(id);
    }

    @PostMapping
    public ExpenseResDTO insert(@Valid @RequestBody ExpenseReqDTO dto){
        return this.service.insert(dto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id")Long id){
         this.service.deleteById(id);
    }

    @PutMapping("{id}")
    public ExpenseResDTO update(@PathVariable("id")Long id , @Valid @RequestBody ExpenseReqDTO dto){
        return this.service.update(id , dto);
    }
}
