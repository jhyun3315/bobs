package com.b304.bobs.api.controller;

import com.b304.bobs.api.dto.CommunityDTO;
import com.b304.bobs.api.dto.ModifyDTO;
import com.b304.bobs.api.dto.PageDTO;
import com.b304.bobs.api.dto.PageResultDTO;
import com.b304.bobs.api.service.CommunityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name ="communities", description = "게시물 API")
@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("api/communities")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getALl(@RequestBody PageDTO pageDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        int page = pageDTO.getPage();
        PageRequest pageRequest = PageRequest.of(page, pageDTO.pageSizeForCommunity(), Sort.by("community_created").descending());

        try {
            PageResultDTO result = communityService.findAll(pageRequest);

            if (result.getContents()==null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("data", result.getContents());
                map.put("total_page", result.getTotalPages());
                map.put("current_page", page+1);
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
        PageRequest pageRequest = PageRequest.of(page, pageDTO.pageSizeForCommunity(),Sort.by("community_created").descending());

        try {
            PageResultDTO result = communityService.findByUser(pageDTO.getUser_id(), pageRequest);
            if (result.getContents() == null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
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


    @GetMapping("/{communityId}")
    public ResponseEntity<?> getOne(@PathVariable("communityId") Long communityId){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityDTO result = communityService.findOneById(communityId);

            if (result.getCommunity_id()==null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
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
    private ResponseEntity<?> create(@RequestBody CommunityDTO communityDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityDTO result = communityService.createCommunity(communityDTO);
            System.out.println(result.getCommunity_id());
            if(result.getCommunity_id() == null){
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
    private ResponseEntity<?> modify(@RequestBody CommunityDTO communityDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(communityDTO.getCommunity_id());

        try {
            ModifyDTO modifyDTO = communityService.modifyCommunity(communityDTO);
            if(modifyDTO.getResult()){
                map.put("community_id",modifyDTO.getId());
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
    private ResponseEntity<?> delete(@RequestParam(value="value") Long community_id){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ModifyDTO modifyDTO = communityService.deleteCommunity(community_id);
            if(modifyDTO.getResult()){
                map.put("community_id",modifyDTO.getId());
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
