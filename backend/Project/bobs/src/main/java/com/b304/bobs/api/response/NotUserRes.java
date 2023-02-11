package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NotUserRes {
    private Long user_id;
    private String user_name;
    private String user_profile;

    public NotUserRes() {

    }
    public NotUserRes(User user) {
        this.user_id = user.getUser_id();
        this.user_name = user.getUser_name();
        this.user_profile = user.getUser_profile();
    }
}
