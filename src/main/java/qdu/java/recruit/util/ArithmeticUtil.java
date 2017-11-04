package qdu.java.recruit.util;

import org.springframework.stereotype.Component;
import qdu.java.recruit.entity.CommentEntity;
import qdu.java.recruit.entity.PositionEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.mapper.*;
import qdu.java.recruit.pojo.PositionCompanyBO;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Component
public class ArithmeticUtil {

    public static ArithmeticUtil ariConst;

    @PostConstruct
    public void init() {
        ariConst = this;
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

    ArrayList<PositionEntity> listPosAll = new ArrayList<PositionEntity>();

    ArrayList<UserEntity> listUserAll = new ArrayList<UserEntity>();

    //基于pv流行性推荐算法
    // map          ->  存在ServletContext中所有职位当日PV数
    // user         ->  当前用户
    // listPos      ->  所有职位 列表
    public ArrayList<PositionCompanyBO> popularityRec(HashMap<Integer, Integer> map, UserEntity user) {

        //所有Position,所有用户
        listPosAll = ariConst.positionMapper.listPosAll();
        listUserAll = ariConst.userMapper.listUser();

        //职位推荐程度值
        double matchDegree = 0.0;

        //城市、职位种类、pv 对应计算推荐程度值权重
        double cityRate = 0.3;
        double categoryRate = 0.7;
        double pvRate = 1;

        //有序TreeMap及ArrayList
        HashMap<Integer, Double> mapOrder = new HashMap<>();
        ArrayList<PositionCompanyBO> listOrder = new ArrayList<>();

        for (PositionEntity pos : listPosAll) {
            //定义该职位当日pv数
            int pv = 0;

            //遍历寻找
            //赋值对应该职位title对应sessionContext内的当日pv数
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                int key = (Integer) entry.getKey();
                if (key == pos.getPositionId()) {                            //getTitle -> getPositionId
                    pv = (Integer) entry.getValue();
                }
            }

            //判断计算推荐程度
            if (pos.getWorkCity().equals(user.getCity())) {
                if (pos.getCategoryId() == user.getDirDesire()) {
                    matchDegree = (pv + 1) * pvRate;
                } else {
                    matchDegree = ((pv + 1) * pvRate) * categoryRate;
                }
            } else {
                if (pos.getCategoryId() == user.getDirDesire()) {
                    matchDegree = ((pv + 1) * pvRate) * cityRate;
                } else {
                    matchDegree = ((pv + 1) * pvRate) * categoryRate * cityRate;
                }
            }

            mapOrder.put(pos.getPositionId(), matchDegree);
        }

        //将mapOrder按照value值(pv数)降序排序
        List<Map.Entry<Integer, Double>> compareList = new ArrayList<>(mapOrder.entrySet());
        Collections.sort(compareList, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (Map.Entry<Integer,Double> mapping:compareList
             ) {
            listOrder.add(ariConst.positionMapper.listPosCompany(mapping.getKey()));
        }

        return listOrder;
    }


//基于用户的协同过滤算法
    //    根据用户应聘，收藏，评价记录给予权重计算得到相似度
    //    选出与当前用户相似度最高的N个用户
    //    查询N用户收藏，而当前用户未收藏的X个记录

    // user     ->  当前用户
    public ArrayList<PositionEntity> synergyUserRec(UserEntity user) {

        //所有Position,所有用户
        listPosAll = ariConst.positionMapper.listPosAll();
        listUserAll = ariConst.userMapper.listUser();

        //当前用户Id
        int userId = user.getUserId();

        //当前向量模 选中向量模
        double vectorNowMod = 0.0;
        double vectorElseMod = 0.0;
        double nowModSqrt = 0.0;
        double elseModSqrt = 0.0;

        //向量积
        double vectorProduct = 0.0;

        //map键值对，键为当前用户Id,值为所有职位对应数组,数组元素 收藏过 为1,未收藏过 为0
        Map<Integer, int[]> colMap = new HashMap<Integer, int[]>();

        //降序排列相似度键值对,键为选中职位与当前职位相似度，值为选中职位Id
        TreeMap<Double, Integer> simMap = new TreeMap<Double, Integer>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if ((o2 - o1) < 0) {
                    return -1;
                } else if ((o2 - o1) == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        //colMap一个元素的值部分 int数组
        int[] itemArray = null;

        //用以标记colMap中当前用户值部分 int数组
        int[] tagArray = null;

        //colMap选中元素键 当前用户Id
        int itemKey = -1;

        int count = 0;
        for (UserEntity u : listUserAll) {
            //矩阵元素填充，用户收藏该职位，则元素值为1，否则值为0
            for (PositionEntity p : listPosAll) {
                if (ariConst.favorMapper.getFavor(u.getUserId(), p.getPositionId()) != null) {
                    itemArray[count] = 1;
                } else {
                    itemArray[count] = 0;
                }
                count++;
            }

            //计算当前用户向量模，并复制到tagArray
            if (u.getUserId() == userId) {
                for (int i = 0; i < itemArray.length; i++) {
                    tagArray[i] = itemArray[i];
                    vectorNowMod += itemArray[i] * itemArray[i];
                }
            }

            //初始化计数值count及单位int数组
            count = 0;
            itemArray = null;

            //将单位 键值对 加入到 colMap中
            colMap.put(u.getUserId(), itemArray);
        }

        //遍历所有职位id对应收藏职位矩阵，计算向量模，点乘积，余弦值
        Iterator iter = colMap.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            itemKey = (int) entry.getKey();
            itemArray = (int[]) entry.getValue();

            if (itemKey != userId) {
                for (int i = 0; i < itemArray.length; i++) {

                    vectorElseMod += itemArray[i] * itemArray[i];

                    vectorProduct += tagArray[i] * itemArray[i];
                }

                nowModSqrt = Math.sqrt(vectorNowMod);
                elseModSqrt = Math.sqrt(vectorElseMod);

                //余弦值为键，用户Id为值得键值对，加入到降序排列相似度simMap中
                simMap.put((vectorProduct / (nowModSqrt * elseModSqrt)), itemKey);
            }
        }

        //将simMap作为参数，找出与当前用户相似度最高的用户收藏，而当前用户未收藏的X条职位记录
        return this.recordRec(userId, simMap);
    }

    public ArrayList<PositionEntity> recordRec(int hostId, TreeMap<Double, Integer> map) {

        //选中用户Id值
        int userId = -1;

        //推荐职位 Id队列
        ArrayList<Integer> posIdList = null;

        //推荐职位 队列
        ArrayList<PositionEntity> recList = null;

        Iterator iter = map.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            userId = (int) entry.getValue();

            posIdList = ariConst.favorMapper.getQuery(userId, hostId);

            for (int posId : posIdList) {
                recList.add(ariConst.positionMapper.getPosition(posId));
            }
        }

        return recList;
    }


    //基于职位协同过滤算法
    //user  ->  当前用户
    public ArrayList<PositionCompanyBO> synergyItemRec(UserEntity user) {

        //所有Position,所有用户
        listPosAll = ariConst.positionMapper.listPosAll();
        listUserAll = ariConst.userMapper.listUser();

        //当前用户Id
        int userId = user.getUserId();

        //当前用户对应简历Id
        int resumeId = ariConst.resumeMapper.getResumeId(userId);

        //定义键为职业Id值，值为每个用户对该职业好感度的键值对图HashMap
        Map<Integer, int[]> favorMap = new HashMap<Integer, int[]>();

        //定义键为 职位Id，值为 当前用户对选定职位的好感度 的键值对图HashMap
        Map<Integer, Integer> defaultMap = new HashMap<Integer, Integer>();

        //用户总数
        int userSize = ariConst.userMapper.getUserSize();

        //定义好感度评分数组
        int[] itemArray = new int[userSize];

        //定义单个职位Id，单个用户Id,单个简历Id
        int posItemId;
        int userItemId;
        int resumeItemId;

        //遍历所有职位，填充好感度键值对图
        for (int i = 0; i < listPosAll.size(); i++) {
            posItemId = listPosAll.get(i).getPositionId();

            for (int j = 0; j < listUserAll.size(); j++) {
                userItemId = listUserAll.get(j).getUserId();
                resumeItemId = ariConst.resumeMapper.getResumeId(userItemId);

                if (ariConst.applicationMapper.getApplication(resumeItemId, posItemId) != null) {
                    itemArray[j] = 3;
                } else {
                    itemArray[j] = 0;
                }

                if (ariConst.favorMapper.getFavor(userItemId, posItemId) != null) {
                    itemArray[j] += 3;
                }

                CommentEntity com = ariConst.commentMapper.listComment(userItemId, posItemId);
                if (com != null) {
                    switch (com.getType()) {
                        case 1:
                            itemArray[j] += 1;
                            break;
                        case 2:
                            itemArray[j] += 2;
                            break;
                        case 3:
                            itemArray[j] += 3;
                            break;
                        default:
                            break;
                    }
                }

                if (userItemId == userId) {
                    //键为职位Id 值为当前用户好感度 的键值对图TreeMap
                    defaultMap.put(posItemId, itemArray[j]);
                }
            }

            favorMap.put(posItemId, itemArray);
            itemArray = new int[userSize];
        }

        //构造当前用户好感度降序排列的职位ArrayList
        List<Map.Entry<Integer, Integer>> defaultEntryList = new ArrayList<Map.Entry<Integer, Integer>>(defaultMap.entrySet());
        Collections.sort(defaultEntryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        ArrayList<Integer> defaultList = new ArrayList<Integer>();

        for (Map.Entry<Integer, Integer> mapping : defaultEntryList) {

            defaultList.add(mapping.getKey());
        }

        //当前用户好感度第一职位
        int favorPosId = defaultList.get(0);

        //好感度第一职位对应数组向量
        int[] favorVector = favorMap.get(favorPosId);

        //与好感度第一职位的相似度键值对HashMap
        HashMap<Integer, Double> similarMap = new HashMap<Integer, Double>();

        //单位数组向量
        int[] itemVector = null;

        //计数器
        int m = 0;
        int n = 0;

        //favorVector,itemVector元素和,均值,模的平方,模，向量积
        double favorSum = 0;
        double itemSum = 0;
        double favorAvg = 0;
        double itemAvg = 0;
        double favorMod = 0;
        double itemMod = 0;
        double favorModSqrt = 0;
        double itemModSqrt = 0;
        double vectorProduct = 0;

        for (int i = 0; i < favorVector.length; i++) {
            favorSum += favorVector[i];
        }
        favorAvg = favorSum / favorVector.length;

        for (int j = 0; j < favorVector.length; j++) {
            favorMod += (favorVector[j] - favorAvg) * (favorVector[j] - favorAvg);
        }
        favorModSqrt = Math.sqrt(favorMod);

        //求每一职位与当前用户好感度第一的职位的相似度
        Iterator iter = favorMap.entrySet().iterator();

        while (iter.hasNext()) {

            itemSum = 0;
            itemAvg = 0;
            itemMod = 0;
            itemModSqrt = 0;
            vectorProduct = 0;

            Map.Entry entry = (Map.Entry) iter.next();
            itemVector = (int[]) entry.getValue();

            for (m = 0; m < itemVector.length; m++) {
                itemSum += itemVector[m];
            }
            itemAvg = itemSum / itemVector.length;

            for (n = 0; n < itemVector.length; n++) {
                vectorProduct += (favorVector[n] - favorAvg) * (itemVector[n] - itemAvg);
                itemMod += (itemVector[n] - itemAvg) * (itemVector[n] - itemAvg);
            }
            itemModSqrt = Math.sqrt(itemMod);

            //相似度键值对,键为职业Id,值为 选定职业与当前职业 皮尔逊相关系数
            similarMap.put((int) entry.getKey(), (vectorProduct / (favorModSqrt * itemModSqrt)));

        }

        //元素为 键为职位Id,值为皮尔逊相关系数的键值对 的ArrayList
        List<Map.Entry<Integer, Double>> similarEntryList = new ArrayList<Map.Entry<Integer, Double>>(similarMap.entrySet());

        //按与选中职业相似度皮尔逊系数降序排序 的元素为职位 的ArrayList
        ArrayList<PositionCompanyBO> similarPosList = new ArrayList<>();

        Collections.sort(similarEntryList, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (Map.Entry<Integer, Double> mapping : similarEntryList
                ) {

            //将没有应聘过的职位加入推荐列表
            if (ariConst.applicationMapper.getApplication(resumeId, mapping.getKey()) == null) {
                similarPosList.add(ariConst.positionMapper.listPosCompany(mapping.getKey()));
            }
        }

        //返回根据皮尔逊系数降序排列的 元素为职位的 职位列表
        return similarPosList;
    }

}
