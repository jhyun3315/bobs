package com.b304.bobs.api.controller;

import com.b304.bobs.api.response.NotUserRes;
import com.b304.bobs.api.response.User.UserRes;
import com.b304.bobs.api.service.User.UserService;
import com.b304.bobs.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping
    public void abc() {
        log.debug("인증이 필요한 서비스에 접근");
    }

    @GetMapping("/find/{userKey}")
    public ResponseEntity<?> findUser(@PathVariable("userKey") String userKey) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            UserRes result = userService.findUser(userKey);

            if (result.getUser_key()==null) {
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

    @GetMapping("/np/{userId}")
    public ResponseEntity<?> findNP(@PathVariable("userId") Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            NotUserRes result = userService.findNP(userId);
            System.out.println(userId);
            if (result.getUser_id()==null) {
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
}
