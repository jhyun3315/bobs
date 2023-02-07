package com.b304.bobs.dto;

public class ModifyDTO {
    private int result;
    private Long id;

    public boolean getResult() {
        return this.result == 1;
    }

    public Long getId() {
        return id;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
