package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.AllergyReq;
import com.b304.bobs.api.response.AllergyRes;
import com.b304.bobs.api.service.AllergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("api/allergy")
public class AllergyController {

    private final AllergyService allergyService;

    @PostMapping
    private ResponseEntity<?> create(AllergyReq allergyReq){
        Map<String, Object> map = new HashMap<>();

        try {
            AllergyRes result = allergyService.createAllergy(allergyReq);
            if (result.getAllergy_id() == null){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else {
                map.put("allergy", result);
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception e){
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

}
