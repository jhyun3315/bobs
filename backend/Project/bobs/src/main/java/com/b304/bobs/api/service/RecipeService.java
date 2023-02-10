package com.b304.bobs.api.service;

import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.RecipeRes;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService {
    public RecipeRes findOneById(Long community_id) throws Exception;
    public PageRes findAll(Pageable pageable) throws Exception;
    public PageRes findByUserLike(Long user_id, Pageable pageable) throws Exception;
}
