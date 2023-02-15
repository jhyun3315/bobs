package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.StudyMember.StudyMemberReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.Study.StudyInfoRes;
import com.b304.bobs.api.response.Study.StudyRes;
import com.b304.bobs.api.response.StudyMember.StudyMemberRes;
import com.b304.bobs.api.service.StudyMember.StudyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/studymembers")
public class StudyMemberController {
    private final StudyMemberService studyMemberService;

    @PostMapping("/info")
    public ResponseEntity<?> getOne(@RequestBody StudyMemberReq studyMemberReq) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long user_id = studyMemberReq.getUser_id();
        Long study_id = studyMemberReq.getStudy_id();

        try {
            StudyInfoRes result = studyMemberService.findOneById(studyMemberReq);

            if (result.equals(new StudyInfoRes())) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            } else {
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
    private ResponseEntity<?> create(@RequestBody StudyMemberReq studyMemberReq){
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            if (studyMemberService.countMember(studyMemberReq.getStudy_id()) > 4) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            StudyMemberRes result = studyMemberService.createStudyMember(studyMemberReq);

            if (result.equals(new StudyMemberRes())) {
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

    @DeleteMapping()
    private ResponseEntity<?> delete(@RequestParam(value="value") Long study_member_id){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ModifyRes modifyRes = studyMemberService.deleteStudyMember(study_member_id);
            if(modifyRes.getResult()){
                map.put("study_member_id", modifyRes.getId());
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