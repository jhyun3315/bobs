package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.CommunityCommentReq;
import com.b304.bobs.api.response.CommunityCommentRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
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
            PageRes result = communityCommentService.findAll(community_id);

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
    private ResponseEntity<?> create(@RequestBody CommunityCommentReq communityCommentReq){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityCommentRes result = communityCommentService.createComment(communityCommentReq);

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
    private ResponseEntity<?> modify(@RequestBody CommunityCommentRes communityCommentRes){
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(communityCommentRes.getCommunity_comment_content());
        System.out.println(communityCommentRes.getCommunity_comment_id());

        try {
            ModifyRes modifyRes = communityCommentService.modifyComment(communityCommentRes);
            if(modifyRes.getResult()){
                map.put("community_id", modifyRes.getId());
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
            ModifyRes modifyRes = communityCommentService.deleteComment(community_comment_id);
            if(modifyRes.getResult()){
                map.put("community_id", modifyRes.getId());
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
