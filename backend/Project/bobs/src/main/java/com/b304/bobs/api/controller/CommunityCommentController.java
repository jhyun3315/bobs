package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.CommunityComment.CommentCheckReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentCreatReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentModifyReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentGetReq;
import com.b304.bobs.api.response.CommunityComment.CommunityCommentRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.service.CommunityComment.CommunityCommentService;
import com.b304.bobs.api.service.User.UserService;
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
@RequestMapping("/community/comment")
public class CommunityCommentController {

    final private CommunityCommentService communityCommentService;
    final private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> getALl(@RequestBody CommunityCommentGetReq communityCommentGetReq){
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            if(!userService.isUserExist(communityCommentGetReq.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            PageRes result = communityCommentService.findAll(communityCommentGetReq);
            map.put("data", result.getContents());
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PostMapping("/write")
    private ResponseEntity<?> create(@RequestBody CommunityCommentCreatReq communityCommentCreatReq){
        Map<String, Object> map = new HashMap<String, Object>();
        Long user_id = communityCommentCreatReq.getUser_id();

        try {
            if(!userService.isUserExist(user_id)){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            CommunityCommentRes result = communityCommentService.createComment(communityCommentCreatReq);

            if(result.getCommunity_comment_id() == null){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }else{
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

    @PutMapping
    private ResponseEntity<?> modify(@RequestBody CommunityCommentModifyReq communityCommentModifyReq) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        CommentCheckReq commentCheckReq = new CommentCheckReq(communityCommentModifyReq);
        boolean check_writer = communityCommentService.findById(commentCheckReq).isCheck_writer();

        try {
            if(!check_writer){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

            if(!userService.isUserExist(communityCommentModifyReq.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            CommunityCommentRes result = communityCommentService.modifyComment(communityCommentModifyReq);
            if(result.isCheck_writer()){
                map.put("data", result);
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
    private ResponseEntity<?> delete(@RequestBody CommentCheckReq commentCheckReq) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Long community_comment_id = commentCheckReq.getCommunity_comment_id();

        boolean check_writer = communityCommentService.findById(commentCheckReq).isCheck_writer();

        try {
            if(!check_writer){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

            if(!userService.isUserExist(commentCheckReq.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            ModifyRes modifyRes = communityCommentService.deleteComment(community_comment_id);

            if(modifyRes.getResult()){
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
