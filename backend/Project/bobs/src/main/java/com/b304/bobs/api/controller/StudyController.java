package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.Page.PageReq;
import com.b304.bobs.api.request.Study.StudyReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.Study.StudyPageRes;
import com.b304.bobs.api.response.Study.StudyRes;
import com.b304.bobs.api.service.Study.StudyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "studies", description = "스터디 API")
@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/studies")
public class StudyController {

    private final StudyService studyService;

    @PutMapping("/lock")
    private ResponseEntity<?> lockStudy(@RequestBody StudyReq studyReq){
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            ModifyRes modifyRes = studyService.lockStudy(studyReq);
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

    @GetMapping
    public ResponseEntity<Map<String, Object>> getALl(@RequestParam(value="page") int page) {
        Map<String, Object> map = new HashMap<>();
        PageReq pageReq = new PageReq(page);
        PageRequest pageRequest = PageRequest.of(pageReq.getPage(), pageReq.pageSizeForCommunity(), Sort.by("study_created").descending());

        try {
            PageRes result = studyService.findAll(pageRequest);

            map.put("result", true);
            map.put("data", result.getContents());
            map.put("total_page", result.getTotalPages());
            map.put("current_page", page);

            return ResponseEntity.status(HttpStatus.OK).body(map);


        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @GetMapping("/full")
    public ResponseEntity<Map<String, Object>> getFullALl(@RequestParam(value="page") int page) {
        Map<String, Object> map = new HashMap<>();
        PageReq pageReq = new PageReq(page);
        PageRequest pageRequest = PageRequest.of(pageReq.getPage(), pageReq.pageSizeForCommunity(), Sort.by("study_created").descending());

        try {
            PageRes result = studyService.findFullAll(pageRequest);

            map.put("data", result.getContents());
            map.put("total_page", result.getTotalPages());
            map.put("current_page", page);
            map.put("result", true);
            return ResponseEntity.status(HttpStatus.OK).body(map);


        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }


    @GetMapping("/{studyId}")
    public ResponseEntity<?> getOne(@PathVariable("studyId") Long studyId){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            StudyReq result = studyService.findOneById(studyId);

            if (result.getStudy_id()==null) {
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

    @PostMapping
    private ResponseEntity<?> create(@RequestBody StudyReq studyReq){
        Map<String, Object> map = new HashMap<String, Object>();

        if(studyReq.getUser_id() == null) {
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }

        try {
            StudyRes result = studyService.createStudy(studyReq);

            if (result.getUser_id() == null) {
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
    private ResponseEntity<?> modify(@RequestBody StudyReq studyReq){
        Map<String, Object> map = new HashMap<String, Object>();
        Long user_id = studyReq.getUser_id();

        if(user_id == null) {
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        try {
            StudyReq studyRe = studyService.findOneById(studyReq.getStudy_id());

            if(studyRe.getStudy_id() == null || !studyRe.getUser_id().equals(user_id)) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            StudyPageRes studyPageRes = studyService.modifyStudy(studyReq);
            System.out.println(studyPageRes.getResult());
            System.out.println(studyPageRes.getStudyRes());

            if(studyPageRes.getResult()){
                map.put("data", studyPageRes.getStudyRes());
                map.put("members", studyPageRes.getStudyMemberResList());
                map.put("member_count", studyPageRes.getMember_count());
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
    private ResponseEntity<?> delete(@RequestParam(value="value") Long study_id){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ModifyRes modifyRes = studyService.deleteStudy(study_id);
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
