package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.StudyComment.StudyCommentDelReq;
import com.b304.bobs.api.request.StudyComment.StudyCommentListReq;
import com.b304.bobs.api.request.StudyComment.StudyCommentModifyReq;
import com.b304.bobs.api.request.StudyComment.StudyCommentReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.StudyComment.StudyCommentRes;
import com.b304.bobs.api.service.StudyComment.StudyCommentService;
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
@RequestMapping("/study/comment")
public class StudyCommentController {

    final private StudyCommentService studyCommentService;
    final private UserService userService;

    @PostMapping("/all")
    public ResponseEntity<Map<String, Object>> getALL(@RequestBody StudyCommentListReq studyCommentListReq){
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            if(!userService.isUserExist(studyCommentListReq.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            PageRes result = studyCommentService.findAll(studyCommentListReq);

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
    private ResponseEntity<?> create(@RequestBody StudyCommentReq studyCommentReq){
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            if(!userService.isUserExist(studyCommentReq.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            StudyCommentRes result = studyCommentService.createComment(studyCommentReq);

            if(result.getStudy_comment_id() == null){
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

    @PutMapping
    private ResponseEntity<?> modify(@RequestBody StudyCommentModifyReq studyCommentModifyReq) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        Long user_id = studyCommentModifyReq.getUser_id();
        Long study_comment_id =  studyCommentModifyReq.getStudy_comment_id();

        try {
            if(!userService.isUserExist(studyCommentModifyReq.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            StudyCommentRes studyCommentRes = studyCommentService.findById(study_comment_id);

            if(!user_id.equals(studyCommentRes.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

            ModifyRes modifyRes = studyCommentService.modifyComment(studyCommentModifyReq);
            if(modifyRes.getResult()){
                map.put("study_comment_id", modifyRes.getId());
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
    private ResponseEntity<?> delete(@RequestBody StudyCommentDelReq studyCommentDelReq) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Long user_id = studyCommentDelReq.getUser_id();
        Long study_comment_id =  studyCommentDelReq.getStudy_comment_id();

        try {

            if(!userService.isUserExist(studyCommentDelReq.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            StudyCommentRes studyCommentRes = studyCommentService.findById(study_comment_id);

            if(!user_id.equals(studyCommentRes.getUser_id())){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

            ModifyRes modifyRes = studyCommentService.deleteComment(study_comment_id);
            if(modifyRes.getResult()){
                map.put("study_id", modifyRes.getId());
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
