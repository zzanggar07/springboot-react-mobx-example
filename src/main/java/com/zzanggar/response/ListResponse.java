package com.zzanggar.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ListResponse<T> {
    @Getter
    private List<T> results;

    @Setter @Getter
    private boolean first;

    @Setter @Getter
    private boolean last;

    @Setter @Getter
    private boolean hasPrevious;

    @Setter @Getter
    private boolean hasNext;

    @Setter @Getter
    private int size;

    @Setter @Getter
    private int totalPages;

    @Getter
    private int page;

    @Getter
    private int totalCount;

    public static <T> ListResponse<T> of() {
        return new ListResponse<>();
    }

    public void setContent(List<T> results) {
        this.results = results;
    }

    public void setTotalElements(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setNumber(int number) {
        this.page = number;
    }

    public ListResponse<T> results(List<T> results, int totalCount, int page) {
        setContent(results);
        setTotalElements(totalCount);
        setNumber(page);
        return this;
    }
}
