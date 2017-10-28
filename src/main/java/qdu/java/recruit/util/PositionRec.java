package qdu.java.recruit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.entity.Collection;
import qdu.java.recruit.service.RecService;

import java.util.*;

@Service
@EnableAutoConfiguration
public class PositionRec {

    @Autowired
    private RecService recService;

    // map          ->  存在ServletContext中所有职位当日PV数
    // user         ->  当前登录用户
    // listPosAll   ->  所有职位 列表
    // listPos      ->  当前用户应聘过的所有评论 列表
    // listCom      ->  当前用户评论过的所有评论 列表
    // listCol      ->  当前用户收藏过的所有评论 列表

    public ArrayList<Position> recommend(HashMap<String, String> map, User user) {

        int userId = user.getUserId();
        int resumeId = recService.findResume(userId);
        ArrayList<Application> listPos = recService.findApplication(resumeId);
        ArrayList<Comment> listCom = recService.findComment(userId);
        ArrayList<Collection> listCol = recService.findCollection(userId);


        //计算用户活跃度，判断采取哪种匹配方式
        double activation = 0;

        //活跃度标准
        double actStandard = 3.0;

        //应聘 评论 收藏得分
        double pointPos = 1.0;
        double pointCom = 0.5;
        double pointCol = 3.0;

        double valuePos = listPos.size() * pointPos;
        double valueCom = listCom.size() * pointCom;
        double valueCol = listCol.size() * pointCol;
        activation = valuePos + valueCom + valueCol;

        RecArithmetic recArithmetic = new RecArithmetic();

        //根据活跃度标准判断调用的推荐算法
        if (activation < actStandard) {

            recArithmetic.popularityRec(map, user);
        } else {

//            recArithmetic.synergyRec(user);
        }

        return null;
    }

}
