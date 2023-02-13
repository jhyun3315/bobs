package com.b304.bobs.api.service;

import com.b304.bobs.api.request.AllergyReq;
import com.b304.bobs.api.request.RefrigeReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.RefrigeRes;
import com.b304.bobs.db.entity.Allergy;
import com.b304.bobs.db.entity.Refrige;
import com.b304.bobs.db.repository.IngredientRepository;
import com.b304.bobs.db.repository.RefrigeRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RefrigeServiceImpl implements RefrigeService {

    private final RefrigeRepository refrigeRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;


    @Override
    @Transactional(readOnly = true)
    public boolean modifyRefrige(RefrigeReq refrigeReq) throws Exception {
        List<Map<String, String>> lst = refrigeReq.getIngredient_list();

        try {
            if (userRepository.findById(refrigeReq.getUser_id()).isEmpty()) {
                return false;
            } else {
                for (Map<String, String> map : lst) {
                    //재료 id
                    Long ingredient_id = Long.valueOf(map.get("ingredient_id"));
                    boolean is_deleted = Boolean.parseBoolean(map.get("is_deleted"));
                    boolean is_prior = Boolean.parseBoolean(map.get("is_prior"));

                    Refrige refrige = refrigeRepository.findByIngredientId(ingredient_id).orElseGet(Refrige::new);

                    refrige.setUser(userRepository.findById(refrigeReq.getUser_id()).get());
                    refrige.setIngredient(ingredientRepository.findById(ingredient_id).get());
                    refrige.setRefrige_ingredient_delete(is_deleted);
                    refrige.setRefrige_ingredient_prior(is_prior);

                    refrigeRepository.save(refrige);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public PageRes findByUser(Long user_id, Pageable pageable) throws Exception{
        PageRes pageRes = new PageRes();

        try {
            Page<Refrige> refriges = refrigeRepository.findByUser(user_id, pageable);
            if(refriges.isEmpty()) return pageRes;
            pageRes
                    .setContents(refriges.stream()
                    .map(RefrigeRes::new)
                    .collect(Collectors.toList())
                    );
            pageRes.setTotalPages(refriges.getTotalPages());
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }
}
