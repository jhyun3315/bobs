package com.b304.bobs.api.service;

import com.b304.bobs.api.response.IngredientRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.db.entity.Ingredient;
import com.b304.bobs.db.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Override
    public PageRes findAll(Pageable pageable) throws Exception {
        PageRes pageRes = new PageRes();

        try {
            Page<Ingredient> ingredients = ingredientRepository.findAll(pageable);

            if(ingredients.isEmpty()) return pageRes;
            pageRes
                    .setContents(ingredients.stream()
                            .map(IngredientRes::new)
                            .collect(Collectors.toList())
                    );
            pageRes.setTotalPages(ingredients.getTotalPages());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageRes;
    }

}
