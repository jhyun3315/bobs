package com.b304.bobs.api.request.User;

import com.b304.bobs.db.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserReq {
    private Long user_id;
    private String user_name;
    private String user_key;
    private boolean user_deleted;

    private String user_profile;
    private String user_email;

    public UserReq() {

    }

    public UserReq(User user) {
        this.user_id = user.getUser_id();
        this.user_name = user.getUser_name();
        this.user_email = user.getUser_email();
        this.user_profile = user.getUser_profile();
        this.user_key = user.getUser_key();
        this.user_deleted = user.getUser_deleted();
    }
}



