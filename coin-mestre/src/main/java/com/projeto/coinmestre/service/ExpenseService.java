package com.projeto.coinmestre.service;

import com.projeto.coinmestre.domain.ExpenseStatus;
import com.projeto.coinmestre.dto.req.ExpenseReqDTO;
import com.projeto.coinmestre.dto.res.ExpenseResDTO;
import com.projeto.coinmestre.dto.res.ExpenseValueResDTO;
import com.projeto.coinmestre.model.Expense;
import com.projeto.coinmestre.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ExpenseService {

    // dizendo que estou injetando a classe ExpenseRopository
    private ExpenseRepository repository;

    //  metodo que busca todas as despesas atravez de uma lista
    public List<ExpenseResDTO> findAllExpenses() {

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

        //       Retornou a lista
        return resDTOList;
    }

    //metodo que busca pelo id as despesas
    public ExpenseResDTO findById(Long id) {
        Optional<Expense> optionalExpense = this.repository.findById(id);
        if (optionalExpense.isPresent()) {
            return new ExpenseResDTO(optionalExpense.get());
        } else {
            throw new RuntimeException("Despesa não encontrada na base de dados");
        }
    }

    //metodo que inseri despesa nova( ExpenseResDTO- que pega informações e ExpenseReqDTO- que passa informações)
    public ExpenseResDTO insert(ExpenseReqDTO dto) {

        Expense expense = ExpenseReqDTO.dtoToModel(dto);

        this.repository.save(expense);

        ExpenseResDTO resDTO = new ExpenseResDTO(expense);

        return resDTO;
    }

    // metodo que deleta uma despesa pelo id
    public void deleteById(Long id) {
        this.repository.deleteById(id);

    }

    //metodo que atualiza uma despesa escolhida pelo numero do id
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

        } else {
//            tratando caso não encontre na base de dados
            throw new RuntimeException("Despesa não encontrada na base de dados.");
        }
    }


    // metodo que soma todos os valores das despesas
    public ExpenseValueResDTO valueOfExpenses() {
        double totalValue = this.repository.findAll().stream().mapToDouble(Expense::getValue).sum();
        long quantit = this.repository.findAll().size();
        return new ExpenseValueResDTO(totalValue, quantit);
    }

    //metodo que soma as despesas que estão abertas

    public ExpenseValueResDTO expensesOpen(){
        List<Expense> expenses = this.repository.findAll();
        List<Expense> openExpenses = new ArrayList<>();

        for (Expense expense : expenses) {
            if (expense.getStatus().equals(ExpenseStatus.OPEN)){
                openExpenses.add(expense);

            }
        }
        double totalValue = openExpenses.stream().mapToDouble(Expense::getValue).sum();
        return new ExpenseValueResDTO(totalValue, openExpenses.size());
    }

    //metodo que soma as despesas que estão pagas
    public ExpenseValueResDTO expensesClose(){
        List<Expense> expenses = this.repository.findAll();
        List<Expense> closeExpenses = new ArrayList<>();

        for (Expense expense : expenses) {
            if (expense.getStatus().equals(ExpenseStatus.CLOSE)){
                closeExpenses.add(expense);

            }
        }
        double totalValue = closeExpenses.stream().mapToDouble(Expense::getValue).sum();
        return new ExpenseValueResDTO(totalValue, closeExpenses.size());
    }
//metodo para filtrar as datas
    public List<ExpenseResDTO> findAllByPurchaseDate(LocalDate initPurchaseDate,
                                                     LocalDate endPurchaseDate) {
        List<Expense> expenseList = this.repository.findAllByPurchaseDateBetween(initPurchaseDate, endPurchaseDate);

        List<ExpenseResDTO> resDTOList = new ArrayList<>();

        for (Expense expense : expenseList) {

            ExpenseResDTO expenseResDTO = new ExpenseResDTO(expense);

            resDTOList.add(expenseResDTO);
        }
        return resDTOList;
    }
}












