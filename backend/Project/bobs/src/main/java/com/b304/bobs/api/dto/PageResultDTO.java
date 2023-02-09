package com.b304.bobs.api.dto;

import java.util.List;

public class PageResultDTO {
    private List<?> contents;
    private int totalPages;

    public PageResultDTO() {
    }

    public PageResultDTO(List<?> contents, int totalPages) {
        this.contents = contents;
        this.totalPages = totalPages;
    }

    public List<?> getContents() {
        return contents;
    }

    public void setContents(List<?> contents) {
        this.contents = contents;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
