package com.b304.bobs.dto;

import com.b304.bobs.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@Data
public class UserDTO {
    private Long user_id;
    private String user_name;
    private String user_key;
    private boolean user_deleted;

    private String user_profile;
    private String user_email;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.user_id = user.getUser_id();
        this.user_name = user.getUser_name();
        this.user_email = user.getUser_email();
        this.user_profile = user.getUser_profile();
        this.user_key = user.getUser_key();
        this.user_deleted = user.getUser_deleted();
    }
}



