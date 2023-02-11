package com.b304.bobs.api.response;

import com.b304.bobs.db.entity.StudyComment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter @Setter
public class StudyCommentRes {
    private Long user_id;
    private Long study_id;
    private Long study_comment_id;
    private String study_comment_content;
    private LocalDateTime study_comment_created;
    private boolean study_comment_deleted;

    public StudyCommentRes() {

    }

    public StudyCommentRes(StudyComment studyComment) {
        this.user_id = studyComment.getUser().getUser_id();
        this.study_comment_id = studyComment.getStudy_comment_id();
        this.study_comment_content = studyComment.getStudy_comment_content();
        this.study_comment_created = studyComment.getStudy_comment_created();
        this.study_comment_deleted = studyComment.getStudy_comment_deleted();
        this.study_id = studyComment.getStudy().getStudy_id();
    }
}
