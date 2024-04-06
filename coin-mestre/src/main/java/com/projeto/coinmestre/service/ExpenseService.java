package com.projeto.coinmestre.service;

import com.projeto.coinmestre.dto.req.ExpenseReqDTO;
import com.projeto.coinmestre.dto.res.ExpenseResDTO;
import com.projeto.coinmestre.model.Expense;
import com.projeto.coinmestre.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {

// dizendo que estou injetando a classe ExpenseRopository
    private ExpenseRepository repository;

//  metodo que busca todas as despesas atravez de uma lista
    public List<ExpenseResDTO> findAllExpenses(){

        //  buscou do banco lista de despesa
        List<Expense> expenseList = this.repository.findAll();

        //  criando a lista de retorno(por enquanto só foi criada, se encontra vazia)
        List<ExpenseResDTO> resDTOList = new ArrayList<>();

        //  ta passando despesa por despesa
        for (Expense expense : expenseList) {

            //  criando um objeto novo ExpenseResDTO com as informações da despesa da vez
            ExpenseResDTO expenseResDTO = new ExpenseResDTO(expense);

            //  ta add o Objeto ExpenseResDTO que foi criado na linha de cima
            resDTOList.add(expenseResDTO);
        }

        //        Retornou a lista
        return resDTOList;
    }
//
    public ExpenseResDTO findById(Long id) {
        Optional<Expense> optionalExpense = this.repository.findById(id);
        if (optionalExpense.isPresent()) {
            return new ExpenseResDTO(optionalExpense.get());
        } else {
            throw new RuntimeException("Despesa não encontrada na base de dados");
        }
    }

    public ExpenseResDTO insert(ExpenseReqDTO dto) {
        Expense expense = ExpenseReqDTO.dtoToModel(dto);
        return new ExpenseResDTO(this.repository.save(expense));
    }

    public void deleteById(Long id){
        this.repository.deleteById(id);

    }

    public ExpenseResDTO update(Long id, ExpenseReqDTO dto) {
//        buscar no banco
        Optional<Expense> optionalExpense = this.repository.findById(id);

//        validando se tinha no banco as informações
        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();

//            atualzando o valor dos atributos com o que veio do cliente
            expense.setDescription(dto.getDescription());
            expense.setValue(dto.getValue());
            expense.setCategory(dto.getCategory());
            expense.setPurchaseDate(dto.getPurchaseDate());
            expense.setDueDate(dto.getDueDate());
            expense.setStatus(dto.getStatus());

//            atualizando as informações no banco
            this.repository.save(expense);

            return new ExpenseResDTO(expense);

        }else {
//            tratando caso não encontre na base de dados
            throw new RuntimeException("Despesa não encontrada na base de dados.");
        }
    }
}




