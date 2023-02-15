package com.b304.bobs.api.request.Study;

import lombok.Getter;

@Getter
public class StudyMeetReq {
    private Long study_id;
    private Long user_id;
    private boolean study_onair;

    public StudyMeetReq() {
    }
}
