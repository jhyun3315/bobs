package com.b304.bobs.api.service;

import com.b304.bobs.api.request.AllergyReq;
import com.b304.bobs.api.response.AllergyRes;
import com.b304.bobs.db.entity.Allergy;
import com.b304.bobs.db.entity.Ingredient;
import com.b304.bobs.db.repository.AllergyRepository;
import com.b304.bobs.db.repository.UserRepository;
import com.b304.bobs.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AllergyServiceImpl implements AllergyService {
    private final AllergyRepository allergyRepository;
    private final UserRepository userRepository;

    @Override
    public boolean createAllergy(AllergyReq allergyReq) throws Exception {
        Map<String, Boolean> map = allergyReq.getAllergy_list();

        System.out.println(allergyReq.getUser_id());

        try{
            if(userRepository.findById(allergyReq.getUser_id()).isEmpty()){
                return false;
            }

            for(String allId: map.keySet()){
                Long allergy_id =  Long.parseLong(allId);
                Allergy allergy = allergyRepository.findByAllergyId(allergy_id).orElseGet(Allergy::new);
                allergy.set_deleted(map.get(allId));

                CommonUtils.saveIfNullId(allergy_id, allergyRepository, allergy);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }
}
