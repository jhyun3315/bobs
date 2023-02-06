package com.b304.bobs.controller;

import com.b304.bobs.dto.CommunityDTO;
import com.b304.bobs.dto.CommunityUpload;
import com.b304.bobs.dto.UserDTO;
import com.b304.bobs.entity.Community;
import com.b304.bobs.service.CommunityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Tag(name ="communities", description = "게시물 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/communities")
public class CommunityController {

    private final CommunityService communityService;

    @ResponseBody
    @GetMapping
    public ResponseEntity<?> getList(Pageable pageable){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<CommunityDTO> communityDTOS = communityService.findAll(pageable);
            if (communityDTOS.isEmpty()) {
//                map.put("status", "NO_CONTENT");
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NO_CONTENT);
            }
            else{
                map.put("communities", communityDTOS);
//                map.put("status", "ok");
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            map.put("status", "fail");
            return new ResponseEntity<Map<String, Object>>(map,HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @GetMapping("/user")
    public ResponseEntity<?> getListById(@RequestBody UserDTO userDTO, Pageable pageable){
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            List<CommunityDTO> communityDTOS = communityService.findByUser(userDTO.getUser_id(), pageable);
            if (communityDTOS.isEmpty()) {
//                map.put("status", "NO_CONTENT");
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NO_CONTENT);
            }
            else{
                map.put("communities", communityDTOS);
//                map.put("status", "ok");
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            map.put("status", "fail");
            return new ResponseEntity<Map<String, Object>>(map,HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{communityId}")
    public ResponseEntity<?> getOne(@PathVariable("communityId") Long communityId){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String,Object> result = communityService.findOneById(communityId);

            if (result.isEmpty()) {
//                map.put("status", "NO_CONTENT");
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NO_CONTENT);
            }
            else{
                map.put("community", result);
//              map.put("status", "ok");
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            map.put("status", "fail");
            return new ResponseEntity<Map<String, Object>>(map,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    private ResponseEntity<?> create(CommunityUpload communityUpload, MultipartFile multipartFile){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityDTO result = communityService.createCommunity(communityUpload);
            if(Objects.equals(result.getUser_id(), communityUpload.getUser_id())){
                map.put("result", 1);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }else{
                map.put("result", 0);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    private ResponseEntity<?> modify(CommunityUpload communityUpload, MultipartFile multipartFile){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityDTO result = communityService.modifyCommunity(communityUpload);
            if(result.getUser_id().equals(communityUpload.getUser_id())){
                map.put("result", 1);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }else{
                map.put("result", 0);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/delete")
    private ResponseEntity<?> delete(CommunityUpload communityUpload){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityDTO result = communityService.deleteCommunity(communityUpload);
            if(result.getCommunity_deleted()){
                map.put("result",1);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
            else {
                map.put("result",0);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
        }
    }

}
