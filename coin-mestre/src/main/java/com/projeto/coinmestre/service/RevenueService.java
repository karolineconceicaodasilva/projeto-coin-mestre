package com.projeto.coinmestre.service;

import com.projeto.coinmestre.base.PageReq;
import com.projeto.coinmestre.base.PageRes;
import com.projeto.coinmestre.domain.RevenueStatus;
import com.projeto.coinmestre.dto.req.RevenueReqDTO;
import com.projeto.coinmestre.dto.res.ExpenseValueResDTO;
import com.projeto.coinmestre.dto.res.RevenueResDTO;
import com.projeto.coinmestre.dto.res.RevenueValueResDTO;
import com.projeto.coinmestre.model.Revenue;
import com.projeto.coinmestre.repository.RevenueRepository;
import com.projeto.coinmestre.util.SearchUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class RevenueService {
    private RevenueRepository repository;

    public PageRes<RevenueResDTO> findAllRevenues(PageReq query) {

        Specification<Revenue> deleted = SearchUtils.specByDeleted(query.isDeleted());
        Specification<Revenue> filters = SearchUtils.specByFilter(query.getFilter(), "id",
                "description", "value", "category", "purchaseDate", "dueDate");

        Page<Revenue> page = this.repository.findAll(deleted.and(filters), query.toPageRequest());

        return new PageRes<>(page.getContent().stream().map(RevenueResDTO::new).collect(Collectors.toList()),
                page.getTotalElements(), page.getTotalPages());
    }

    public RevenueResDTO findById(Long id) {
        Optional<Revenue> optionalRevenue = this.repository.findById(id);
        if (optionalRevenue.isPresent()) {
            return new RevenueResDTO(optionalRevenue.get());
        } else {
            throw new RuntimeException("Receita não encontrada na base de dados");
        }
    }

    public RevenueResDTO insert(RevenueReqDTO dto) {
        Revenue revenue = RevenueReqDTO.dtoToModel(dto);
        this.repository.save(revenue);
        RevenueResDTO resDTO = new RevenueResDTO(revenue);
        return resDTO;
    }

    public void restoreDeleted(Long id) {

        if (this.repository.findDeletedById(id).isEmpty())
            throw new RuntimeException("Receita não encontrada na base de dados.");

        this.repository.restoreDeleted(id);
    }

    public void logicalExclusion(Long id) {

        if (this.repository.findByIdAndNotDeleted(id).isEmpty())
            throw new RuntimeException("Receita não encontrada na base de dados.");

        this.repository.softDelete(id);
    }

    public RevenueResDTO update(Long id, RevenueReqDTO dto) {

        Optional<Revenue> optionalRevenue = this.repository.findById(id);

        if (optionalRevenue.isPresent()) {
            Revenue revenue = optionalRevenue.get();

            revenue.setDescription(dto.getDescription());
            revenue.setValue(dto.getValue());
            revenue.setPurchaseDate(dto.getPurchaseDate());
            revenue.setDueDate(dto.getDueDate());
            revenue.setStatus(dto.getStatus());

            this.repository.save(revenue);

            return new RevenueResDTO(revenue);

        } else {

            throw new RuntimeException("Receita não encontrada na base de dados.");
        }
    }

    public RevenueValueResDTO valueOfRevenues() {
        double totalValue = this.repository.findAll().stream().mapToDouble(Revenue::getValue).sum();
        long quantit = this.repository.findAll().size();
        return new RevenueValueResDTO(totalValue, quantit);
    }

    public RevenueValueResDTO revenuesOpen() {
        List<Revenue> revenues = this.repository.findAll();
        List<Revenue> openRevenues = new ArrayList<>();

        for (Revenue revenue : revenues) {
            if (revenue.getStatus().equals(RevenueStatus.OPEN)) {
                openRevenues.add(revenue);

            }
        }
        double totalValue = openRevenues.stream().mapToDouble(Revenue::getValue).sum();
        return new RevenueValueResDTO(totalValue, openRevenues.size());
    }

    public RevenueValueResDTO revenuesClose() {
        List<Revenue> revenues = this.repository.findAll();
        List<Revenue> closeRevenues = new ArrayList<>();

        for (Revenue revenue : revenues) {
            if (revenue.getStatus().equals(RevenueStatus.CLOSE)) {
                closeRevenues.add(revenue);

            }
        }
        double totalValue = closeRevenues.stream().mapToDouble(Revenue::getValue).sum();
        return new RevenueValueResDTO(totalValue, closeRevenues.size());
    }

    public RevenueValueResDTO revenuesOverdue() {
        List<Revenue> revenues = this.repository.findAll();
        List<Revenue> overdueRevenues = new ArrayList<>();

        for (Revenue revenue : revenues) {
            if (revenue.getStatus().equals(RevenueStatus.OVERDUE)) {
                overdueRevenues.add(revenue);

            }
        }
        double totalValue = overdueRevenues.stream().mapToDouble(Revenue::getValue).sum();
        return new RevenueValueResDTO(totalValue, overdueRevenues.size());
    }


    public List<RevenueResDTO> findAllByPurchaseDate(LocalDate initPurchaseDate,
                                                     LocalDate endPurchaseDate) {
        List<Revenue> revenueList = this.repository.findAllByPurchaseDateBetween(initPurchaseDate, endPurchaseDate);

        List<RevenueResDTO> resDTOList = new ArrayList<>();

        for (Revenue revenue : revenueList) {

            RevenueResDTO revenueResDTO = new RevenueResDTO(revenue);

            resDTOList.add(revenueResDTO);
        }
        return resDTOList;
    }


}












