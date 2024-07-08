package com.projeto.coinmestre.service;

import com.projeto.coinmestre.base.PageReq;
import com.projeto.coinmestre.base.PageRes;
import com.projeto.coinmestre.dto.req.BudgetReqDTO;
import com.projeto.coinmestre.dto.res.BudgetResDTO;
import com.projeto.coinmestre.model.Budget;
import com.projeto.coinmestre.model.User;
import com.projeto.coinmestre.repository.BudgetRepository;
import com.projeto.coinmestre.util.SearchUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BudgetService {

    private BudgetRepository repository;
    private UserService userService;

    public PageRes<BudgetResDTO> findAllBudgets(PageReq query) {

        Specification<Budget> deleted = SearchUtils.specByDeleted(query.isDeleted());
        Specification<Budget> filters = SearchUtils.specByFilter(query.getFilter(), "id",
                "goal", "totalReached", "totalGoal");
        Specification<Budget> userSpecification = SearchUtils.specByUser(this.userService.findByLoggedUserEntity());

        Page<Budget> page = this.repository.findAll(deleted.and(filters).and(userSpecification), query.toPageRequest());

        return new PageRes<>(page.getContent().stream().map(BudgetResDTO::new).collect(Collectors.toList()),
                page.getTotalElements(), page.getTotalPages());
    }


    public BudgetResDTO findById(Long id) {
        Optional<Budget> optionalBudget = this.repository.findById(id);
        if (optionalBudget.isPresent()) {
            return new BudgetResDTO(optionalBudget.get());
        } else {
            throw new RuntimeException("Orçamento não encontrada na base de dados");
        }
    }

    public BudgetResDTO insert(BudgetReqDTO dto) {

        Budget budget = BudgetReqDTO.dtoToModel(dto);

        User user = this.userService.findByLoggedUserEntity();
        budget.setUser(user);
        this.repository.save(budget);

        BudgetResDTO resDTO = new BudgetResDTO(budget);

        return resDTO;
    }

    public void restoreDeleted(Long id) {

        if (this.repository.findDeletedById(id).isEmpty())
            throw new RuntimeException("Orçamento não encontrado na base de dados.");

        this.repository.restoreDeleted(id);
    }

    public void logicalExclusion(Long id) {

        if (this.repository.findByIdAndNotDeleted(id).isEmpty())
            throw new RuntimeException("Orçamento não encontrado na base de dados.");

        this.repository.softDelete(id);
    }


    public BudgetResDTO update(Long id, BudgetReqDTO dto) {

        Optional<Budget> optionalBudget = this.repository.findById(id);
        if (optionalBudget.isPresent()) {
            Budget budget = optionalBudget.get();
            budget.setGoal(dto.getGoal());
            budget.setTotalReached(dto.getTotalReached());

            this.repository.save(budget);

            return new BudgetResDTO(budget);
        } else {

            throw new RuntimeException("Orçamento não encontrado na base de dados.");
        }
    }
}



