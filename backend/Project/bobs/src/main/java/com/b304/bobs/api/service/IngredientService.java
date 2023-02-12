package com.b304.bobs.api.service;

import com.b304.bobs.api.response.PageRes;
import org.springframework.data.domain.Pageable;

public interface IngredientService  {
    PageRes findAll(Pageable pageable) throws Exception;
}
