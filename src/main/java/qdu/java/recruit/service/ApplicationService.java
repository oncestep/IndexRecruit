package qdu.java.recruit.service;


import qdu.java.recruit.pojo.ApplicationPositionHRBO;

import java.util.List;

public interface ApplicationService {

    boolean applyPosition(int resumeId, int positionId);

    List<ApplicationPositionHRBO> listApplyInfo(int resumeId);

    List<ApplicationPositionHRBO> listApplyInfoPub(int resumeId);

    List<ApplicationPositionHRBO> listApplyInfoByHr(int hrid);

}
