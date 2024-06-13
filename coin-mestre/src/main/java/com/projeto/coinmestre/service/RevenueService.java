package com.projeto.coinmestre.service;

import com.projeto.coinmestre.domain.RevenueStatus;
import com.projeto.coinmestre.dto.req.RevenueReqDTO;
import com.projeto.coinmestre.dto.res.ExpenseResDTO;
import com.projeto.coinmestre.dto.res.RevenueResDTO;
import com.projeto.coinmestre.dto.res.RevenueValueResDTO;
import com.projeto.coinmestre.model.Expense;
import com.projeto.coinmestre.model.Revenue;
import com.projeto.coinmestre.repository.ExpenseRepository;
import com.projeto.coinmestre.repository.RevenueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class RevenueService {
    private RevenueRepository repository;

    public List<RevenueResDTO> findAllRevenues() {

        List<Revenue> revenueList = this.repository.findAll();

        List<RevenueResDTO> resDTOList = new ArrayList<>();

        for (Revenue revenue : revenueList) {

            RevenueResDTO RevenueResDTO = new RevenueResDTO(revenue);

            resDTOList.add(RevenueResDTO);
        }

        return resDTOList;
    }

    public RevenueResDTO findById(Long id) {
        Optional<Revenue> optionalRevenue = this.repository.findById(id);
        if (optionalRevenue.isPresent()) {
            return new RevenueResDTO(optionalRevenue.get());
        } else {
            throw new RuntimeException("Despesa não encontrada na base de dados");
        }
    }

    public RevenueResDTO insert(RevenueReqDTO dto) {
        Revenue revenue = RevenueReqDTO.dtoToModel(dto);
        this.repository.save(revenue);
        RevenueResDTO resDTO = new RevenueResDTO(revenue);
        return resDTO;
    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);

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
    public RevenueValueResDTO revenuesClose(){
        List<Revenue> revenues= this.repository.findAll();
        List<Revenue> closeRevenues = new ArrayList<>();

        for (Revenue revenue : revenues) {
            if (revenue.getStatus().equals(RevenueStatus.CLOSE)){
                closeRevenues.add(revenue);

            }
        }
        double totalValue = closeRevenues.stream().mapToDouble(Revenue::getValue).sum();
        return new RevenueValueResDTO(totalValue, closeRevenues.size());
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












