package qdu.java.recruit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.DepartmentEntity;
import qdu.java.recruit.entity.PositionEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.mapper.CompanyMapper;
import qdu.java.recruit.mapper.DepartmentMapper;
import qdu.java.recruit.mapper.PositionMapper;
import qdu.java.recruit.pojo.PositionCompanyBO;
import qdu.java.recruit.service.PositionService;
import qdu.java.recruit.util.RecPositionUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private PositionMapper positionMapper;


    /**
     * 分页推荐职位
     * @param user
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageInfo<PositionCompanyBO> recPosition(UserEntity user, int page, int limit) {

        //所有职位列表
        List<PositionEntity> posList = new ArrayList<>();
        posList = positionMapper.listPosAll();

        //计算得推荐职位列表
        List<PositionCompanyBO> recList = new ArrayList<>();

        //所有职位Id -> 点击量
        HashMap<Integer,Integer> posMap = new HashMap<Integer, Integer>();
        for (PositionEntity pos : posList
                ) {
            posMap.put(pos.getPositionId(),pos.getHits());
        }

        RecPositionUtil rec = new RecPositionUtil();

        //返回推荐职位ArrayList
        recList = rec.recommend(posMap,user);

        PageHelper.startPage(page,limit);
        PageInfo<PositionCompanyBO> pageInfo = new PageInfo<>(recList);

        LOGGER.debug("Exit recPosition method");
        return pageInfo;
    }

    /**
     * 分页职位搜索
     * @param keyword
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageInfo<PositionCompanyBO> searchPosition(String keyword, int page, int limit){

        PageHelper.startPage(page, limit);

        List<PositionCompanyBO> searchList = positionMapper.listSearchPos("%"+keyword+"%");

        return new PageInfo<>(searchList);
    }

    /**
     * 各分类职位索引页
     * @param categoryId
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageInfo<PositionCompanyBO> listPosition(int categoryId, int page, int limit){
        int total = positionMapper.countCategoryPos(categoryId);
        PageHelper.startPage(page,limit);
        List<PositionCompanyBO> posList = positionMapper.listCategoryPos(categoryId);
        PageInfo<PositionCompanyBO> pagination = new PageInfo<>(posList);
        pagination.setTotal(total);
        return pagination;
    }

    /**
     * 根据职位Id查找返回职位
     * @param positionId
     * @return
     */
    @Override
    public PositionEntity getPositionById(int positionId){
        return positionMapper.getPosition(positionId);
    }


    @Override
    public boolean updateHits(int positionId){
        if(positionMapper.updateHits(positionId) > 0){
            return true;
        }
        return false;
    }

}
