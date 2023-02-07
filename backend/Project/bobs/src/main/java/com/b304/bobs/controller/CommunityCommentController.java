package com.b304.bobs.controller;

import com.b304.bobs.dto.*;
import com.b304.bobs.service.CommunityCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/community/comment")
public class CommunityCommentController {

    final private CommunityCommentService communityCommentService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getALl(@RequestBody PageDTO pageDTO){
        Map<String, Object> map = new HashMap<String, Object>();

        int page = pageDTO.getPage();
        PageRequest pageRequest = PageRequest.of(page, pageDTO.pageSizeForComment(), Sort.by("community_comment_created").descending());

        try {
            PageResultDTO result = communityCommentService.findAll(pageDTO.getCommunity_id(), pageRequest);

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


}
