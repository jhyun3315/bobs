package com.b304.bobs.api.service;

import com.b304.bobs.api.response.IngredientRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.db.entity.Ingredient;
import com.b304.bobs.db.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Override
    @Cacheable("ingredients")
    public PageRes findAll() throws Exception {
        System.out.println("ingredients call!!!!!!!");
        PageRes pageRes = new PageRes();

        try {
            List<Ingredient> ingredients = ingredientRepository.findAll();

            if(ingredients.isEmpty()) return pageRes;
            pageRes
                    .setContents(ingredients.stream()
                            .map(IngredientRes::new)
                            .collect(Collectors.toList())
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageRes;
    }

}
