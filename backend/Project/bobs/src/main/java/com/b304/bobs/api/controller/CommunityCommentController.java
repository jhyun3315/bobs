package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.CommentDelReq;
import com.b304.bobs.api.request.CommunityCommentReq;
import com.b304.bobs.api.response.CommunityCommentRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.service.CommunityCommentService;
import com.b304.bobs.db.entity.CommunityComment;
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
    private ResponseEntity<?> modify(@RequestBody CommunityCommentRes communityCommentRes) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        Long user_id = communityCommentRes.getUser_id();
        Long community_comment_id =  communityCommentRes.getCommunity_comment_id();

        Long Origin_id = communityCommentService.findById(community_comment_id).getUser_id();

        if(!user_id.equals(Origin_id)){
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
        }

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
    private ResponseEntity<?> delete(@RequestBody CommentDelReq commentDelReq) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        Long user_id = commentDelReq.getUser_id();
        Long community_comment_id =  commentDelReq.getCommunity_comment_id();

        CommunityCommentRes communityCommentRes = communityCommentService.findById(community_comment_id);

        if(!user_id.equals(communityCommentRes.getUser_id())){
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
        }

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
