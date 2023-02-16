package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.Page.PageReq;
import com.b304.bobs.api.request.Study.StudyLockReq;
import com.b304.bobs.api.request.Study.StudyMeetReq;
import com.b304.bobs.api.request.Study.StudyReq;
import com.b304.bobs.api.request.Study.StudyUserPageReq;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.response.Study.StudyMeetRes;
import com.b304.bobs.api.response.Study.StudyModifyRes;
import com.b304.bobs.api.response.Study.StudyRes;
import com.b304.bobs.api.service.Study.StudyService;
import com.b304.bobs.api.service.StudyMember.StudyMemberService;
import com.b304.bobs.db.repository.UserRepository;
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
    private final StudyMemberService studyMemberService;
    private final UserRepository userRepository;

    @PutMapping("/meet")
    private ResponseEntity<?> meeet(@RequestBody StudyMeetReq studyMeetReq){
        Map<String, Object> map = new HashMap<String, Object>();

        Long user_id = studyMeetReq.getUser_id();
        Long study_id = studyMeetReq.getStudy_id();

        try {
            if(!studyService.findOneById(study_id).getUser_id().equals(user_id)){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            StudyMeetRes studyMeetRes = studyService.studyOnair(studyMeetReq);

            if(studyMeetRes.isResult()){
                map.put("result", true);
                map.put("study_id", studyMeetRes.getStudy_id());
                map.put("study_onair", studyMeetRes.isStudy_onair());
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }else{
                map.put("result", false);
                map.put("study_id", studyMeetRes.getStudy_id());
                map.put("study_onair", studyMeetRes.isStudy_onair());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }


    @PutMapping("/lock")
    private ResponseEntity<?> lockStudy(@RequestBody StudyLockReq studyLockReq){
        Map<String, Object> map = new HashMap<String, Object>();
        Long user_id = studyLockReq.getUser_id();
        Long study_id = studyLockReq.getStudy_id();

        try {
            //방장이 아니면 false
            if(!studyService.findOneById(study_id).getUser_id().equals(user_id)){
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            ModifyRes modifyRes = studyService.lockStudy(studyLockReq);

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

    @PostMapping
    public ResponseEntity<Map<String, Object>> getALl(@RequestBody PageReq pageReq) {
        Map<String, Object> map = new HashMap<>();
        PageRequest pageRequest = PageRequest.of(pageReq.getPage(), pageReq.pageSizeForCommunity(), Sort.by("study_created").descending());
        Long user_id = pageReq.getUser_id();

        try {
            PageRes result = studyService.findAll(pageRequest, user_id);

            map.put("result", true);
            map.put("data", result.getContents());
            map.put("total_page", result.getTotalPages());
            map.put("current_page", pageReq.getPage()+1);

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

    @PostMapping("/user")
    public ResponseEntity<Map<String, Object>> getAllByUser(@RequestBody StudyUserPageReq studyUserPageReq) {
        Map<String, Object> map = new HashMap<>();
        Long user_id = studyUserPageReq.getUser_id();

        try {
            PageRes result = studyMemberService.findAllByUser(user_id);

            map.put("data", result.getContents());
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

    @PostMapping("/write")
    private ResponseEntity<?> create(@RequestBody StudyReq studyReq){
        Map<String, Object> map = new HashMap<String, Object>();

        if(userRepository.isUserExist(studyReq.getUser_id()).isEmpty()) {
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
        Long study_id = studyReq.getStudy_id();

        if(userRepository.isUserExist(studyReq.getUser_id()).isEmpty()) {
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        try {
            StudyReq studyRes = studyService.findOneById(study_id);

            if(!studyRes.getUser_id().equals(user_id)) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            StudyModifyRes studyModifyRes = studyService.modifyStudy(studyReq);

            if(!studyModifyRes.equals(new StudyModifyRes())){
                map.put("data", studyModifyRes);
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
    private ResponseEntity<?> delete(@RequestBody StudyUserPageReq studyUserPageReq){
        Map<String, Object> map = new HashMap<String, Object>();
        Long user_id = studyUserPageReq.getUser_id();
        Long study_id = studyUserPageReq.getStudy_id();

        try {
            if(!studyService.findOneById(study_id).getUser_id().equals(user_id)){
                map.put("result",false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }

            ModifyRes modifyRes = studyService.deleteStudy(study_id);

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
