package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.Refrige.RefrigeReq;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.service.Refrige.RefrigeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/refriges")
public class RefrigeController {

    private final RefrigeService refrigeService;

    @PostMapping()
    public ResponseEntity<?> getListById(@RequestBody Long user_id) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            PageRes result = refrigeService.findByUser(user_id);

            map.put("data", result.getContents());
            return ResponseEntity.status(HttpStatus.OK).body(map);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PutMapping
    private ResponseEntity<?> modify(@RequestBody RefrigeReq refrigeReq){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            boolean result = refrigeService.modifyRefrige(refrigeReq);
            if(result){
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }else{
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }



}
