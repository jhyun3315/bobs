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

@Tag(name = "refriges", description = "냉장고 API")
@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/api/refriges")
public class RefrigeController {

    private final RefrigeService refrigeService;

    @PostMapping("/user")
    public ResponseEntity<?> getListById(@RequestBody PageReq pageReq){
        Map<String, Object> map = new HashMap<String, Object>();
        int page = pageReq.getPage();
        PageRequest pageRequest = PageRequest.of(page, pageReq.pageSizeForCommunity());

        try {
            PageRes result = refrigeService.findByUser(pageReq.getUser_id(), pageRequest);
            if (result.getContents() == null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }else{
                map.put("data", result.getContents());
                map.put("total_page", result.getTotalPages());
                map.put("current_page",page+1);
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody RefrigeReq refrigeReq){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            RefrigeReq result = refrigeService.createRefrige(refrigeReq);
            if (result.getRefrige_id()==null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("refrige", result);
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
        System.out.println(refrigeReq.getRefrige_id());

        try {
            ModifyRes modifyRes = refrigeService.modifyRefrige(refrigeReq);
            if(modifyRes.getResult()){
                map.put("refrige_id", modifyRes.getId());
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

    @DeleteMapping()
    private ResponseEntity<?> delete(@RequestParam(value="value") Long refrige_id){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ModifyRes modifyRes = refrigeService.deleteRefrige(refrige_id);
            if(modifyRes.getResult()){
                map.put("refrige_id", modifyRes.getId());
                map.put("result",true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
            else {
                map.put("result",false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

}
