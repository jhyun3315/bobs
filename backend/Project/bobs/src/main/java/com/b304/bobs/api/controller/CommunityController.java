package com.b304.bobs.api.controller;

import com.b304.bobs.api.request.CommunityReq;
import com.b304.bobs.api.response.CommunityRes;
import com.b304.bobs.api.response.ModifyRes;
import com.b304.bobs.api.request.PageReq;
import com.b304.bobs.api.response.PageRes;
import com.b304.bobs.api.service.CommunityService;
import com.b304.bobs.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("api/communities")
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page") int page) {
        PageReq pageReq = new PageReq(page);

        Map<String, Object> map = new HashMap<String, Object>();
        PageRequest pageRequest = PageRequest.of(pageReq.getPage(), pageReq.pageSizeForCommunity(), Sort.by("community_created").descending());

        try {
            PageRes result = communityService.findAll(pageRequest);

            if (result.getContents() == null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            } else {
                map.put("data", result.getContents());
                map.put("total_page", result.getTotalPages());
                map.put("current_page", page + 1);
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> getListById(@RequestBody PageReq pageReq) {
        Map<String, Object> map = new HashMap<String, Object>();
        int page = pageReq.getPage();
        PageRequest pageRequest = PageRequest.of(page, pageReq.pageSizeForCommunity(), Sort.by("community_created").descending());

        try {
            PageRes result = communityService.findByUser(pageReq.getUser_id(), pageRequest);
            if (result.getContents() == null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            } else {
                map.put("data", result.getContents());
                map.put("total_page", result.getTotalPages());
                map.put("current_page", page + 1);
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }


    @GetMapping("/{communityId}")
    public ResponseEntity<?> getOne(@PathVariable("communityId") Long communityId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommunityRes result = communityService.findOneById(communityId);

            if (result.getCommunity_id() == null) {
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
    private ResponseEntity<?> create(CommunityReq communityReq) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            if (!FileUtils.validImgFile(communityReq.getCommunity_img().getInputStream())
                    && communityReq.getCommunity_img().getSize() != 0) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

            CommunityRes result = communityService.createCommunity(communityReq);

            if (result.getCommunity_id() == null) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            } else {
                map.put("community", result);
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
    private ResponseEntity<?> modify(@RequestBody CommunityReq communityReq) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            if (!FileUtils.validImgFile(communityReq.getCommunity_img().getInputStream())) {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(map);
            }

            ModifyRes modifyRes = communityService.modifyCommunity(communityReq);
            if (modifyRes.getResult()) {
                map.put("community_id", modifyRes.getId());
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            } else {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @DeleteMapping()
    private ResponseEntity<?> delete(@RequestParam(value = "value") Long community_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ModifyRes modifyRes = communityService.deleteCommunity(community_id);
            if (modifyRes.getResult()) {
                map.put("community_id", modifyRes.getId());
                map.put("result", true);
                return ResponseEntity.status(HttpStatus.OK).body(map);
            } else {
                map.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

}
