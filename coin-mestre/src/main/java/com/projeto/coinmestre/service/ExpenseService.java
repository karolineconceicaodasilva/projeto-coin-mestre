package com.projeto.coinmestre.service;

import com.projeto.coinmestre.base.PageReq;
import com.projeto.coinmestre.base.PageRes;
import com.projeto.coinmestre.domain.ExpenseStatus;
import com.projeto.coinmestre.dto.req.ExpenseReqDTO;
import com.projeto.coinmestre.dto.res.ExpenseResDTO;
import com.projeto.coinmestre.dto.res.ExpenseValueResDTO;
import com.projeto.coinmestre.model.Expense;
import com.projeto.coinmestre.model.User;
import com.projeto.coinmestre.repository.ExpenseRepository;
import com.projeto.coinmestre.util.SearchUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ExpenseService {

    // dizendo que estou injetando a classe ExpenseRopository
    private ExpenseRepository repository;
    private UserService userService;

    //  metodo que busca todas as despesas atravez de uma lista
    public PageRes<ExpenseResDTO> findAllExpenses(PageReq query) {

        Specification<Expense> deleted = SearchUtils.specByDeleted(query.isDeleted());
        Specification<Expense> filters = SearchUtils.specByFilter(query.getFilter(), "id",
                "description", "value", "category", "purchaseDate", "dueDate");
        Specification<Expense> userSpecification = SearchUtils.specByUser(this.userService.findByLoggedUserEntity());

        Page<Expense> page = this.repository.findAll(deleted.and(filters).and(userSpecification), query.toPageRequest());

        return new PageRes<>(page.getContent().stream().map(ExpenseResDTO::new).collect(Collectors.toList()),
                page.getTotalElements(), page.getTotalPages());
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
        User user = this.userService.findByLoggedUserEntity();
        expense.setUser(user);
        this.repository.save(expense);

        ExpenseResDTO resDTO = new ExpenseResDTO(expense);

        return resDTO;
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
        User user = this.userService.findByLoggedUserEntity();
        List<Expense> expenses = this.repository.findAllByUserAndDeletedIsFalse(user);
        double totalValue = expenses.stream().mapToDouble(Expense::getValue).sum();
        long quantit = expenses.size();
        return new ExpenseValueResDTO(totalValue, quantit);
    }

    //metodo que soma as despesas que estão abertas

    public ExpenseValueResDTO expensesOpen() {
        User user = this.userService.findByLoggedUserEntity();
        List<Expense> expenses = this.repository.findAllByUserAndDeletedIsFalse(user);
        List<Expense> openExpenses = new ArrayList<>();

        for (Expense expense : expenses) {
            if (expense.getStatus().equals(ExpenseStatus.OPEN)) {
                openExpenses.add(expense);
            }
        }
        double totalValue = openExpenses.stream().mapToDouble(Expense::getValue).sum();
        return new ExpenseValueResDTO(totalValue, openExpenses.size());
    }

    //metodo que soma as despesas que estão pagas
    public ExpenseValueResDTO expensesClose() {
        User user = this.userService.findByLoggedUserEntity();
        List<Expense> expenses = this.repository.findAllByUserAndDeletedIsFalse(user);
        List<Expense> closeExpenses = new ArrayList<>();

        for (Expense expense : expenses) {
            if (expense.getStatus().equals(ExpenseStatus.CLOSE)) {
                closeExpenses.add(expense);

            }
        }
        double totalValue = closeExpenses.stream().mapToDouble(Expense::getValue).sum();
        return new ExpenseValueResDTO(totalValue, closeExpenses.size());
    }

    public ExpenseValueResDTO expensesOverdue() {
        User user = this.userService.findByLoggedUserEntity();
        List<Expense> expenses = this.repository.findAllByUserAndDeletedIsFalse(user);
        List<Expense> closeExpenses = new ArrayList<>();

        for (Expense expense : expenses) {
            if (expense.getStatus().equals(ExpenseStatus.OVERDUE)) {
                closeExpenses.add(expense);

            }
        }

        double totalValue = closeExpenses.stream().mapToDouble(Expense::getValue).sum();
        return new ExpenseValueResDTO(totalValue, closeExpenses.size());
    }

    public void logicalExclusion(Long id) {

        if (this.repository.findByIdAndNotDeleted(id).isEmpty())
            throw new RuntimeException("Despesa não encontrada na base de dados.");

        this.repository.softDelete(id);
    }

    public void restoreDeleted(Long id) {

        if (this.repository.findDeletedById(id).isEmpty())
            throw new RuntimeException("Despesa não encontrada na base de dados.");

        this.repository.restoreDeleted(id);
    }
}












