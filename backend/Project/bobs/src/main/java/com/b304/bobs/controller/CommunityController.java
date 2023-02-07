package com.b304.bobs.controller;

import com.b304.bobs.dto.CommunityDTO;
import com.b304.bobs.dto.PageDTO;
import com.b304.bobs.entity.Community;
import com.b304.bobs.service.CommunityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Tag(name ="communities", description = "게시물 API")
@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/communities")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getALl(@RequestBody PageDTO pageDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        int page = pageDTO.getPage();
        PageRequest pageRequest = PageRequest.of(page, pageDTO.pageSizeForCommunity());

        try {
            Page<Community> result = communityService.findAll(pageRequest);

            if (result.isEmpty()) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("communities", result.getContent());
                map.put("total_page", result.getTotalPages());
                map.put("current_page", page);
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getListById(@RequestBody PageDTO pageDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        int page = pageDTO.getPage();
        System.out.println(pageDTO.getUser_id());
        PageRequest pageRequest = PageRequest.of(page, pageDTO.pageSizeForCommunity());

        try {
            Page<Community>  result = communityService.findByUser(pageDTO.getUser_id(), pageRequest);
            if (result.isEmpty()) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("communities", result.getContent());
                map.put("total_page", result.getTotalPages());
                map.put("current_page",page);
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }


    @GetMapping("/{communityId}")
    public ResponseEntity<?> getOne(@PathVariable("communityId") Long communityId){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String,Object> result = communityService.findOneById(communityId);

            if (result.isEmpty()) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
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

    @PostMapping
    private ResponseEntity<?> create(CommunityDTO communityDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityDTO result = communityService.createCommunity(communityDTO);
            if(Objects.equals(result.getUser_id(), communityDTO.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
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
    private ResponseEntity<?> modify(CommunityDTO communityDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityDTO result = communityService.modifyCommunity(communityDTO);
            if(result.getUser_id().equals(communityDTO.getUser_id())){
                map.put("community_id",result.getCommunity_id());
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

    @PutMapping("/delete")
    private ResponseEntity<?> delete(Long community_id){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityDTO result = communityService.deleteCommunity(community_id);
            if(result.getCommunity_deleted()){
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
