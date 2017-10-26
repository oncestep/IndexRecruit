package qdu.java.recruit.util;


import org.springframework.beans.factory.annotation.Autowired;
import qdu.java.recruit.domain.HR;
import qdu.java.recruit.domain.Position;
import qdu.java.recruit.domain.User;
import qdu.java.recruit.service.RecServiceImpl;

import java.util.*;

//@Service
//@EnableAutoConfiguration
public class UserRec {

    @Autowired
    private RecServiceImpl recService;

    //所有User列表
    ArrayList<User> listUserAll = recService.findUserAll();

    //基于内容的推荐
    //当前HR
    public ArrayList<User> contentRec(HR hr){

        //当前HRId
        int hrId = hr.getHrId();

        //定义当前HR发布的职位列表，按发布日期降序排列
        ArrayList<Position> posList = new ArrayList<Position>();

        posList = recService.findPosHR(hrId);

        //定义选中职位分类Id,工作城市
        int itemPosId = -1;
        String itemPosCity = null;

        //用户与选中职位基于工作分类 及 城市 匹配得分
        int matchScore = 0;

        //键为用户Id 值为匹配得分 键值对HashMap
        HashMap<Integer,Integer> matchMap = new HashMap<Integer, Integer>();

        for (Position pos:posList
             ) {
            itemPosId = pos.getCategoryId();
            itemPosCity = pos.getWorkCity();
            matchScore = 0;

            for (User user:listUserAll
                 ) {

                if(user.getDirDesire() == itemPosId){
                    if(user.getCity() == itemPosCity){
                        matchScore = 3;
                    }else{
                        matchScore = 2;
                    }
                }else{
                    if(user.getCity() == itemPosCity){
                        matchScore = 1;
                    }else{
                        matchScore = 0;
                    }
                }

                if(matchMap.containsKey(user.getUserId())){
                    //对于一个用户,比较的匹配得分最高的该hr发布职位，刷新匹配得分
                    matchMap.put(user.getUserId(),((matchMap.get(user.getUserId())>matchScore)?matchMap.get(user.getUserId()):matchScore));
                }else{
                    matchMap.put(user.getUserId(),matchScore);
                }
            }
        }


        //元素为单个键值对,键为用户Id,职位匹配得分
        List<Map.Entry<Integer,Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(matchMap.entrySet());

        //元素为推荐用户
        ArrayList<User> userRecList = new ArrayList<User>();

        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (Map.Entry<Integer,Integer> mapping:entryList
             ) {
            userRecList.add(recService.findUser(mapping.getKey()));
        }

        return userRecList;
    }
}
