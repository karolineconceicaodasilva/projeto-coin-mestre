package com.projeto.coinmestre.base;

import lombok.Data;

import java.util.List;

@Data
public class PageRes<T> {

    private List<T> content;
    private long totalElements;
    private int totalPages;

    public PageRes(List<T> content, long totalElements, int totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

}
