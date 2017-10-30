package qdu.java.recruit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.Position;
import qdu.java.recruit.entity.User;
import qdu.java.recruit.mapper.PositionMapper;
import qdu.java.recruit.service.PositionService;
import qdu.java.recruit.utils.PositionRec;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private PositionMapper positionMapper;

    @Override
    public PageInfo<Position> recPosition(User user,int page,int limit) {

        //所有职位列表
        List<Position> posList = new ArrayList<Position>();
        posList = positionMapper.listPosAll();

        //计算得推荐职位列表
        List<Position> recList = new ArrayList<Position>();

        //所有职位Id -> 点击量
        HashMap<Integer,Integer> posMap = new HashMap<Integer, Integer>();
        for (Position pos : posList
                ) {
            posMap.put(pos.getPositionId(),pos.getHits());
        }

        PositionRec rec = new PositionRec();

        //返回推荐职位ArrayList
        recList = rec.recommend(posMap,user);

        PageHelper.startPage(page,limit);
        PageInfo<Position> pageInfo = new PageInfo<>(recList);

        LOGGER.debug("Exit getContents method");
        return pageInfo;
    }
}
