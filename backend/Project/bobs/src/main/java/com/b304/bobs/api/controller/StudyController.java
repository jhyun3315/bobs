package com.b304.bobs.api.controller;

import com.b304.bobs.api.dto.*;
import com.b304.bobs.api.service.StudyService;
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
@RequestMapping("/api/studies")
public class StudyController {

    private final StudyService studyService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getALl(@RequestBody PageDTO pageDTO) {
        Map<String, Object> map = new HashMap<>();
        int page = pageDTO.getPage();
        PageRequest pageRequest = PageRequest.of(page, pageDTO.pageSizeForCommunity(), Sort.by("study_created").descending());

        try {

            PageResultDTO result = studyService.findAll(pageRequest);

            if (result.getContents() == null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            } else {
                map.put("data", result.getContents());
                map.put("total_page", result.getTotalPages());
                map.put("current_page", page+1);
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }

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
            StudyDTO result = studyService.findOneById(studyId);

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
    private ResponseEntity<?> create(@RequestBody StudyDTO studyDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            StudyDTO result = studyService.createStudy(studyDTO);
            if (result.getStudy_id()==null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
            else{
                map.put("study", result);
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
    private ResponseEntity<?> modify(@RequestBody StudyDTO studyDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(studyDTO.getStudy_id());

        try {
            ModifyDTO modifyDTO = studyService.modifyStudy(studyDTO);
            if(modifyDTO.getResult()){
                map.put("study_id",modifyDTO.getId());
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
            ModifyDTO modifyDTO = studyService.deleteStudy(study_id);
            if(modifyDTO.getResult()){
                map.put("study_id",modifyDTO.getId());
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
