package com.b304.bobs.api.service;

import com.b304.bobs.api.request.RefrigeReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.db.entity.Refrige;
import com.b304.bobs.db.repository.RefrigeRepository;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RefrigeServiceImpl implements RefrigeService {

    private final RefrigeRepository refrigeRepository;

    private final UserRepository userRepository;

    @Override
    public RefrigeReq createRefrige(RefrigeReq refrigeReq) throws Exception {

        Refrige refrige = new Refrige();
        RefrigeReq result = new RefrigeReq();

        try {
            refrige.setRefrige_ingredient_delete(false);
            refrige.setRefrige_ingredient_prior(false);

            // 재료 관련 추가 필요 **

            if (refrigeRepository.findById(refrigeReq.getUser_id()).isPresent()) {
                refrige.setUser(userRepository.findById(refrigeReq.getUser_id()).orElse(null));
                result = new RefrigeReq(refrigeRepository.save(refrige));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ModifyRes deleteRefrige(Long refrige_id) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            int result = refrigeRepository.deleteRefrigeById(refrige_id);
            modifyRes.setResult(result);
            modifyRes.setId(refrige_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modifyRes;
    }

    @Override
    public ModifyRes modifyRefrige(RefrigeReq refrigeReq) throws Exception {
        ModifyRes modifyRes = new ModifyRes();

        try {
            int result = refrigeRepository.modifyRefrige(
                    refrigeReq.getRefrige_ingredient_prior(),
                    refrigeReq.getRefrige_id());

            modifyRes.setResult(result);
            modifyRes.setId(refrigeReq.getRefrige_id());
            return modifyRes;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modifyRes;
    }

    @Override
    public PageRes findByUser(Long user_id, Pageable pageable) throws Exception{
        PageRes pageRes = new PageRes();

        try {
            Page<Refrige> refriges = refrigeRepository.findByUser(user_id, pageable);
            if(refriges.isEmpty()) return pageRes;
            pageRes
                    .setContents(refriges.stream()
                    .map(RefrigeReq::new)
                    .collect(Collectors.toList())
                    );
            pageRes.setTotalPages(refriges.getTotalPages());
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageRes;
    }
}
