package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class UserRes {
    private Long user_id;
    private String user_name;
    private String user_profile;
    private String user_key;

    public UserRes() {

    }
    public UserRes(User user) {
        this.user_id = user.getUser_id();
        this.user_key = user.getUser_key();
        this.user_name = user.getUser_name();
        this.user_profile = user.getUser_profile();
    }
}
