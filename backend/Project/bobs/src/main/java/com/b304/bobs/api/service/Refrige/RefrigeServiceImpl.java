package com.b304.bobs.api.service.Refrige;

import com.b304.bobs.api.request.Refrige.RefrigeReq;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.Refrige.RefrigeRes;
import com.b304.bobs.db.entity.Refrige;
import com.b304.bobs.db.repository.IngredientRepository;
import com.b304.bobs.db.repository.RefrigeRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    public boolean modifyRefrige(RefrigeReq refrigeReq) throws Exception {
        List<Map<String, String>> lst = refrigeReq.getIngredient_list();
        Long user_id = refrigeReq.getUser_id();

        try {
            if (userRepository.findById(refrigeReq.getUser_id()).isEmpty()) {
                return false;
            } else {
                for (Map<String, String> map : lst) {
                    Long ingredient_id = Long.valueOf(map.get("ingredient_id"));
                    boolean is_deleted = Boolean.parseBoolean(map.get("is_deleted"));
                    boolean is_prior = Boolean.parseBoolean(map.get("is_prior"));

                    boolean is_Exist = refrigeRepository.isExistIngredient(ingredient_id, user_id).isPresent();
                    // 재료가 존재한다면
                    System.out.println("재료가 이미 존재하나? :"+is_Exist);
                    if(is_Exist){
                        int deletedFlag = (is_deleted) ? 1:0;
                        int priorFlag = (is_prior) ? 1:0;
                        System.out.println("활겅화: "+deletedFlag +" "+"우선순위 : "+priorFlag);
                        refrigeRepository.modifyRefrige(priorFlag ,deletedFlag, user_id);

                    }else{
                        // 존재 하지 않으면 새로 생성
                        Refrige refrige = new Refrige();
                        refrige.setUser(userRepository.findById(refrigeReq.getUser_id()).get());
                        refrige.setIngredient(ingredientRepository.findById(ingredient_id).get());
                        refrige.setRefrige_ingredient_delete(is_deleted);
                        refrige.setRefrige_ingredient_prior(is_prior);

                        refrigeRepository.save(refrige);
                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public PageRes findByUser(Long user_id) throws Exception{
        PageRes pageRes = new PageRes();

        try {
            List<Refrige> refriges = refrigeRepository.findByUser(user_id);
            if(refriges.isEmpty()) return pageRes;
            pageRes
                    .setContents(refriges.stream()
                    .map(RefrigeRes::new)
                    .collect(Collectors.toList())
                    );
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }
}
