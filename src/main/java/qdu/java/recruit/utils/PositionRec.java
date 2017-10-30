package qdu.java.recruit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.mapper.*;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class PositionRec {

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private FavorMapper favorMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private UserMapper userMapper;

    public static PositionRec positionRec;

    @PostConstruct
    public void init(){
        positionRec = this;
    }

    // map          ->  存在ServletContext中所有职位当日PV数
    // user         ->  当前登录用户
    // listPosAll   ->  所有职位 列表
    // listPos      ->  当前用户应聘过的所有评论 列表
    // listCom      ->  当前用户评论过的所有评论 列表
    // listCol      ->  当前用户收藏过的所有评论 列表

    public ArrayList<Position> recommend(HashMap<Integer, Integer> map, User user) {

        int userId = user.getUserId();
        int resumeId = positionRec.resumeMapper.getResumeId(userId);
        ArrayList<Application> listPos = applicationMapper.listApplication(resumeId);
        ArrayList<Comment> listCom = commentMapper.listCommentAll(userId);
        ArrayList<Favor> listCol = favorMapper.listFavor(userId);


        //计算用户活跃度，判断采取哪种匹配方式
        double activation = 0;

        //活跃度标准
        double actStandard = 3.0;

        //推荐Position列表
        ArrayList<Position> posList = new ArrayList<Position>();

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

            posList = recArithmetic.popularityRec(map, user);
        } else {

            posList = recArithmetic.synergyItemRec(user);
        }

        return posList;
    }

}
