package com.b304.bobs.dto;

import com.b304.bobs.entity.Study;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Data
public class StudyDTO {
    private Long study_id;
    private String study_content;
    private LocalDateTime study_created;
    private boolean study_deleted;
    private boolean study_lock;
    private String study_time;
    private String study_title;
    private Long user_id;

    public StudyDTO() {

    }
    public StudyDTO(Study study) {
        this.study_id = study.getStudy_id();
        this.study_content = study.getStudy_content();
        this.study_created = study.getStudy_created();
        this.study_deleted = study.getStudy_deleted();
        this.study_lock = study.getStudy_lock();
        this.study_time = study.getStudy_time();
        this.study_title = study.getStudy_title();
        this.user_id = study.getUser().getUser_id();
    }
}
