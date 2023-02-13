package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.PageReq;
import com.b304.bobs.api.request.RefrigeReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.service.RefrigeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/api/refriges")
public class RefrigeController {

    private final RefrigeService refrigeService;

    @PostMapping()
    public ResponseEntity<?> getListById(@RequestBody PageReq pageReq){
        Map<String, Object> map = new HashMap<String, Object>();
        PageRequest pageRequest = PageRequest.of(pageReq.getPage(), pageReq.pageSizeForCommunity());

        try {
            PageRes result = refrigeService.findByUser(pageReq.getUser_id(), pageRequest);
            if (result.getContents() == null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }else{
                map.put("data", result.getContents());
                map.put("total_page", result.getTotalPages());
                map.put("current_page",pageReq.getPage()+1);
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
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
