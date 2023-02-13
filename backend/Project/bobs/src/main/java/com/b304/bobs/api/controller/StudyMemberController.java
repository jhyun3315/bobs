package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.StudyMemberReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.service.StudyMemberService;
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

    @GetMapping("/{studyId}")
    public ResponseEntity<?> countMember(@PathVariable("studyId") Long studyId){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long result = studyMemberService.countMember(studyId);

            if (result == null) {
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