package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.CommunityComment.CommentCheckReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentModiReq;
import com.b304.bobs.api.request.CommunityComment.CommunityCommentReq;
import com.b304.bobs.api.response.CommunityComment.CommunityCommentRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.NotUserRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.service.CommunityComment.CommunityCommentService;
import com.b304.bobs.api.service.User.UserService;
import com.b304.bobs.db.entity.User;
import com.b304.bobs.db.repository.UserRepository;
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
    public ResponseEntity<Map<String, Object>> getALl(@RequestBody CommentCheckReq commentCheckReq){
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            PageRes result = communityCommentService.findAll(commentCheckReq);
            map.put("data", result.getContents());
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PostMapping("/write")
    private ResponseEntity<?> create(@RequestBody CommunityCommentReq communityCommentReq){
        Map<String, Object> map = new HashMap<String, Object>();
        Long user_id = communityCommentReq.getUser_id();

        try {

            if(!userService.isUserExist(user_id)){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            CommunityCommentRes result = communityCommentService.createComment(communityCommentReq);

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
    private ResponseEntity<?> modify(@RequestBody CommunityCommentModiReq communityCommentModiReq) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        CommentCheckReq commentCheckReq = new CommentCheckReq(communityCommentModiReq);
        boolean check_writer = communityCommentService.findById(commentCheckReq).isCheck_writer();

        if(!check_writer){
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
        }

        try {
            CommunityCommentRes result = communityCommentService.modifyComment(communityCommentModiReq);
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
    private ResponseEntity<?> delete(@RequestBody CommentCheckReq commentDelReq) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Long community_comment_id = commentDelReq.getCommunity_comment_id();
        boolean check_writer = communityCommentService.findById(commentDelReq).isCheck_writer();

        if(!check_writer){
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
