package com.b304.bobs.dto;

import java.util.List;

public class PageResultDTO {
    private List<CommunityDTO> contents;
    private int totalPages;

    public PageResultDTO() {
    }

    public PageResultDTO(List<CommunityDTO> contents, int totalPages) {
        this.contents = contents;
        this.totalPages = totalPages;
    }

    public List<CommunityDTO> getContents() {
        return contents;
    }

    public void setContents(List<CommunityDTO> contents) {
        this.contents = contents;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
