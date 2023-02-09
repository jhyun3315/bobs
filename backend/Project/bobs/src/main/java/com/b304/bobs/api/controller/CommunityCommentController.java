package com.b304.bobs.api.controller;

import com.b304.bobs.api.dto.*;
import com.b304.bobs.api.service.CommunityCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@ResponseBody
@RestController
@RequestMapping("api/community/comment")
public class CommunityCommentController {

    final private CommunityCommentService communityCommentService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getALl(@RequestParam(value="value") Long community_id){
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            PageResultDTO result = communityCommentService.findAll(community_id);

            if (result.getContents()==null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("data", result.getContents());
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
    private ResponseEntity<?> create(@RequestBody CommunityCommentDTO communityCommentDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityCommentDTO result = communityCommentService.createComment(communityCommentDTO);

            if(result.getCommunity_comment_id() == null){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("community_id", result.getCommunity_id());
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
    private ResponseEntity<?> modify(@RequestBody CommunityCommentDTO communityCommentDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(communityCommentDTO.getCommunity_comment_content());
        System.out.println(communityCommentDTO.getCommunity_comment_id());

        try {
            ModifyDTO modifyDTO = communityCommentService.modifyComment(communityCommentDTO);
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
    private ResponseEntity<?> delete(@RequestParam(value="value") Long community_comment_id){
        System.out.println(community_comment_id);

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ModifyDTO modifyDTO = communityCommentService.deleteComment(community_comment_id);
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
