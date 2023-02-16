package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.Community.CommunityCheckReq;
import com.b304.bobs.api.request.Community.CommunityReq;
import com.b304.bobs.api.response.Community.CommunityRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.service.Community.CommunityService;
import com.b304.bobs.api.service.User.UserService;
import com.b304.bobs.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/communities")
public class CommunityController {

    private final CommunityService communityService;
    private final UserService userService;

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
            if(!userService.isUserExist(user_id)){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            PageRes result = communityService.findByUser(user_id);
            map.put("data", result.getContents());
            return ResponseEntity.status(HttpStatus.OK).body(map);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }


    @PostMapping("/{communityId}")
    public ResponseEntity<?> getOne(@RequestBody CommunityCheckReq communityCheckReq) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(!userService.isUserExist(communityCheckReq.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            CommunityRes result = communityService.findOneById(communityCheckReq.getCommunity_id(), communityCheckReq.getUser_id());

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
        Map<String, Object> map = new HashMap<>();

        try {
            if(!userService.isUserExist(communityReq.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            //이미지 파일이 아닐경우 false
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

            if(!userService.isUserExist(communityReq.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            Long user_id = communityReq.getUser_id();
            Long community_id =  communityReq.getCommunity_id();
            String file_name = communityReq.getCommunity_file_name();
            MultipartFile community_img = communityReq.getCommunity_img();

            CommunityRes communityRes = communityService.findOneById(community_id ,user_id);

            // 작성자와 요청자가 같은지 확인
            if(!communityRes.isCheck_writer()){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

            // 이미지 파일이 아닌경우
            if (!FileUtils.validImgFile(community_img.getInputStream()) && community_img.getSize() != 0) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

//            System.out.println("텍스트화된 파일이름: "+file_name);
//            System.out.println("보낸 파일이름: "+community_img.getOriginalFilename());
//            System.out.println("보낸 파일의 타입"+community_img.getContentType());

            // 기존의 이미지와 동일한 경우
            String origin_file_name = communityRes.getCommunity_img();
            boolean check_update =
                    (Objects.equals(community_img.getOriginalFilename(), "") && file_name.equals(origin_file_name));

//            System.out.println("업데이트 되었나?: "+check_update);
            ModifyRes modifyRes = communityService.modifyCommunity(communityReq, check_update);

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
    private ResponseEntity<?> delete(@RequestBody CommunityCheckReq communityCheckReq) throws Exception {
        Map<String, Object> map = new HashMap<>();

        Long user_id = communityCheckReq.getUser_id();
        Long community_id =  communityCheckReq.getCommunity_id();

        try {

            if(!userService.isUserExist(user_id)){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            boolean is_writer = communityService.findOneById(community_id,user_id).isCheck_writer();
            if(!is_writer){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

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
