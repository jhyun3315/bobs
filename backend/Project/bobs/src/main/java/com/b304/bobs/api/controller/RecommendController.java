package com.b304.bobs.api.controller;

import com.b304.bobs.api.service.Recommend.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommendations")
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getRecommendations(@PathVariable Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            recommendService.getRecommend(userId);
            map.put("result", true);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (Exception e){
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }

    }
}
