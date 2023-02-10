package com.b304.bobs.api.controller;

import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.StudyCommentRes;
import com.b304.bobs.api.service.StudyCommentService;
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
@RequestMapping("api/study/comment")
public class StudyCommentController {
    final private StudyCommentService studyCommentService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getALl(@RequestParam(value="value") Long study_id){
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            PageRes result = studyCommentService.findAll(study_id);

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
    private ResponseEntity<?> create(@RequestBody StudyCommentRes studyCommentRes){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            StudyCommentRes result = studyCommentService.createComment(studyCommentRes);

            if(result.getStudy_comment_id() == null){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("study_id", result.getStudy_id());
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
    private ResponseEntity<?> modify(@RequestBody StudyCommentRes studyCommentRes){
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(studyCommentRes.getStudy_comment_content());
        System.out.println(studyCommentRes.getStudy_comment_id());

        try {
            ModifyRes modifyRes = studyCommentService.modifyComment(studyCommentRes);
            if(modifyRes.getResult()){
                map.put("study_id", modifyRes.getId());
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
    private ResponseEntity<?> delete(@RequestParam(value="value") Long study_comment_id){
        System.out.println(study_comment_id);

        Map<String, Object> map = new HashMap<String, Object>();
        try {
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
