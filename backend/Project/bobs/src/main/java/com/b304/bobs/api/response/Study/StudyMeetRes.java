package com.b304.bobs.api.response.Study;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudyMeetRes {
    private Long study_id;
    private boolean study_onair;
    private boolean result;

    public StudyMeetRes() {
    }
}
