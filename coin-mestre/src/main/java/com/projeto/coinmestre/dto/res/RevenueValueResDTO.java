package com.projeto.coinmestre.dto.res;

import com.projeto.coinmestre.domain.ExpenseStatus;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RevenueValueResDTO {

    public RevenueValueResDTO(Double value, ExpenseStatus status, long quantit ){
        this.valueTotal = value;
        this.status = status;
        this.quantit = quantit;
    }

    public RevenueValueResDTO(Double value, long quantit){
        this.valueTotal = value;
        this.quantit = quantit;
    }

    private Double valueTotal;
    private ExpenseStatus status;
    private long quantit;
}






