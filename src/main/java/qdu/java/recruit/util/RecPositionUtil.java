package qdu.java.recruit.util;

import org.springframework.stereotype.Component;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.mapper.*;
import qdu.java.recruit.pojo.PositionCompanyBO;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Component
public class RecPositionUtil {

    public static RecPositionUtil posConst;

    @PostConstruct
    public void init() {
        posConst = this;
    }

    @Resource
    private ApplicationMapper applicationMapper;

    @Resource
    private FavorMapper favorMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private PositionMapper positionMapper;

    @Resource
    private ResumeMapper resumeMapper;

    @Resource
    private UserMapper userMapper;

    // map          ->  存在ServletContext中所有职位当日PV数
    // user         ->  当前登录用户
    // listPosAll   ->  所有职位 列表
    // listPos      ->  当前用户应聘过的所有评论 列表
    // listCom      ->  当前用户评论过的所有评论 列表
    // listCol      ->  当前用户收藏过的所有评论 列表

    public ArrayList<PositionCompanyBO> recommend(HashMap<Integer, Integer> map, UserEntity user) {

        int userId = user.getUserId();
        int resumeId = 0;

        if(posConst.resumeMapper.getResumeById(userId)!= null){
            resumeId = posConst.resumeMapper.getResumeId(userId);
        }

        //应聘记录
        ArrayList<ApplicationEntity> listPos = posConst.applicationMapper.listApplication(resumeId);
        //评论记录
        ArrayList<CommentEntity> listCom = posConst.commentMapper.listCommentAll(userId);
        //收藏记录
        ArrayList<FavorEntity> listCol = posConst.favorMapper.listFavor(userId);

        //计算用户活跃度，判断采取哪种匹配方式
        double activation = 0;

        //活跃度标准
        double actStandard = 3.0;

        //推荐Position列表
        ArrayList<PositionCompanyBO> posList = new ArrayList<>();

        //应聘 评论 收藏得分
        double pointPos = 1.0;
        double pointCom = 0.5;
        double pointCol = 3.0;

        double valuePos = listPos.size() * pointPos;
        double valueCom = listCom.size() * pointCom;
        double valueCol = listCol.size() * pointCol;
        activation = valuePos + valueCom + valueCol;

        ArithmeticUtil recArithmetic = new ArithmeticUtil();

        //根据活跃度标准判断调用的推荐算法
        if (activation < actStandard) {

            posList = recArithmetic.popularityRec(map, user);
        } else {

            posList = recArithmetic.synergyItemRec(user);
        }

        return posList;
    }

}
