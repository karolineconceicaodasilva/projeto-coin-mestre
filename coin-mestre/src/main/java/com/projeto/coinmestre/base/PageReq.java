package com.projeto.coinmestre.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageReq {

    private int page;
    private int size;
    private String[] sort;
    private String filter;
    private boolean deleted;

    public PageRequest toPageRequest() {
        if (size == 0)
            size = Integer.MAX_VALUE;
        return PageRequest.of(page, size, sortBy());
    }

    private Sort sortBy() {
        Sort sortObj;
        if (sort != null && sort.length > 0) {
            List<Order> orders = new ArrayList<>();
            for (int i = 0; i < sort.length; i++) {
                if (sort[i].contains(":"))
                    orders.add(new Order(Sort.Direction.fromString(sort[i].split(":")[1]), sort[i].split(":")[0]));
                else
                    orders.add(new Order(Direction.ASC, sort[i]));
            }
            sortObj = Sort.by(orders);
        }
        else {
            sortObj = Sort.by(new Order(Direction.ASC, "id"));
        }
        return sortObj;
    }
}
