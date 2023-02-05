package com.b304.bobs.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name ="communities", description = "게시뭏 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/communities")
public class CommunityController {

//    private final CommunityService communityService;

//    @GetMapping
//    public  List<Community> getList(Model model){
//        return communityService.findAll();
//    }
//
//
//    @PostMapping
//    public CreateCommunityResponse writeCommunity(@RequestBody @Validated Community community){
//        communityService.write(community);
//        return new CreateCommunityResponse(community.getCommunity_id());
//    }

//    @PostMapping
//    public CreateCommunityResponse writeCommunity(@RequestBody @Validated CreateCommunityRequest request){
//        Community community = new Community();
//
//        community.setUser(request.user_id); //service에서 findId해서 찾기~
//
//        community.setCommunity_title(request.getTitle());
//        community.setCommunity_content(request.content);
//        community.setCommunity_img(request.img);
//        communityService.write(request);
//    }

}
