package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.Community.CommunityDelReq;
import com.b304.bobs.api.request.Community.CommunityReq;
import com.b304.bobs.api.response.Community.CommunityRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.service.Community.CommunityService;
import com.b304.bobs.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/communities")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            PageRes result = communityService.findAll();
            map.put("data", result.getContents());
            return ResponseEntity.status(HttpStatus.OK).body(map);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> getListById(@RequestBody long user_id) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            PageRes result = communityService.findByUser(user_id);
            map.put("data", result.getContents());
            return ResponseEntity.status(HttpStatus.OK).body(map);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }


    @GetMapping("/{communityId}")
    public ResponseEntity<?> getOne(@PathVariable("communityId") Long communityId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityRes result = communityService.findOneById(communityId);

            if (result.getCommunity_id() == null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            } else {
                map.put("data", result);
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
    private ResponseEntity<?> create(CommunityReq communityReq) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            if (!FileUtils.validImgFile(communityReq.getCommunity_img().getInputStream())
                    && communityReq.getCommunity_img().getSize() != 0) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

            CommunityRes result = communityService.createCommunity(communityReq);

            if (result.getCommunity_id() == null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            } else {
                map.put("community", result);
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
    private ResponseEntity<?> modify(CommunityReq communityReq) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        try {

            Long user_id = communityReq.getUser_id();
            Long community_id =  communityReq.getCommunity_id();
            Long Origin_id = communityService.findOne(community_id).getUser_id();
            MultipartFile community_img = communityReq.getCommunity_img();

            // 작성자와 요청자가 같은지 확인
            if(!user_id.equals(Origin_id)){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

            // 이미지 파일이 아닌경우
            if (!FileUtils.validImgFile(community_img.getInputStream()) && community_img.getSize() != 0) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

            ModifyRes modifyRes = communityService.modifyCommunity(communityReq);

            if (modifyRes.getResult()) {
                map.put("data", modifyRes.getObject());
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            } else {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @DeleteMapping()
    private ResponseEntity<?> delete(@RequestBody CommunityDelReq communityDelReq) throws Exception {
        Map<String, Object> map = new HashMap<>();

        Long user_id = communityDelReq.getUser_id();
        Long community_id =  communityDelReq.getCommunity_id();

        Long Origin_id = communityService.findOne(community_id).getUser_id();
        if(!user_id.equals(Origin_id)){
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
        }

        try {
            ModifyRes modifyRes = communityService.deleteCommunity(community_id);
            if (modifyRes.getResult()) {
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            } else {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

}
