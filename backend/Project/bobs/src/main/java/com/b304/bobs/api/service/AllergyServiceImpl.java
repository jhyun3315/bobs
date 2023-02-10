package com.b304.bobs.api.service;

import com.b304.bobs.api.request.AllergyReq;
import com.b304.bobs.api.response.AllergyRes;
import com.b304.bobs.db.entity.Allergy;
import com.b304.bobs.db.entity.Ingredient;
import com.b304.bobs.db.repository.AllergyRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllergyServiceImpl implements AllergyService {
    private final AllergyRepository allergyRepository;
    private final UserRepository userRepository;

    @Override
    public AllergyRes createAllergy(AllergyReq allergyReq) throws Exception {
        Allergy allergy = new Allergy();
        AllergyRes result = new AllergyRes();

        try{
            allergy.set_deleted(false);

            if (userRepository.findById(allergyReq.getUser_id()).isPresent()){
                allergy.setUser(userRepository.findById(allergyReq.getUser_id()).orElse(null));
                result = new AllergyRes(allergyRepository.save(allergy));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
