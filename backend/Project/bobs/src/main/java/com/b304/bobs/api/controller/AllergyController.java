package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.AllergyReq;
import com.b304.bobs.api.request.PageReq;
import com.b304.bobs.api.response.AllergyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.service.AllergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("api/allergy")
public class AllergyController {

    private final AllergyService allergyService;

    @PostMapping
    private ResponseEntity<?> create(@RequestBody AllergyReq allergyReq){
        Map<String, Object> map = new HashMap<>();

        try {
            boolean result = allergyService.createAllergy(allergyReq);
            if(result) {
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }else{
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PostMapping("/user")
    private ResponseEntity<?> getAllById(@RequestBody PageReq pageReq){
        Map<String, Object> map = new HashMap<>();
        System.out.println(pageReq.getPage());

        PageRequest pageRequest = PageRequest.of(pageReq.getPage(), pageReq.pageSizeForCommunity(), Sort.by("allergy_name").ascending());

        try {
            PageRes result = allergyService.findById(pageReq.getUser_id(), pageRequest);

            if(result.getContents().isEmpty()) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }else{
                map.put("result", true);
                map.put("data", result.getContents());
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }
}
