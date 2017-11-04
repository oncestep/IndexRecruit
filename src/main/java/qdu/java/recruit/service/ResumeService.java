package qdu.java.recruit.service;

import qdu.java.recruit.entity.ResumeEntity;

public interface ResumeService {

    ResumeEntity getResumeById(int userId);

    boolean updateResume(ResumeEntity resumeEntity);

    boolean createResume(ResumeEntity resumeEntity);
}
