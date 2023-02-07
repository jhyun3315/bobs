package com.b304.bobs.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "studies", description = "스터디 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/studies")
public class StudyController {

//    private final StudyService studyService;

//    @ResponseBody
//    @GetMapping
//    public ResponseEntity<?> getList(Pageable pageable) {
//        Map<String, Object> map = new HashMap<>();
//
//
//    }

}
