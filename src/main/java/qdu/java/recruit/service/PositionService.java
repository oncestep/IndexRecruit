package qdu.java.recruit.service;

import com.github.pagehelper.PageInfo;
import qdu.java.recruit.entity.PositionEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.pojo.PositionCompanyBO;

import java.util.List;


public interface PositionService {

    /**
     * 分页推荐职位
     *
     * @param user
     * @return
     */
    List<PositionCompanyBO> recPosition(UserEntity user, int page, int limit);

    /**
     * 分页职位搜索
     *
     * @param keyword
     * @param page
     * @param limit
     * @return
     */
    PageInfo<PositionCompanyBO> searchPosition(String keyword, String orderBy, int page, int limit);

    /**
     * 各分类职位索引页
     *
     * @param categoryId
     * @param page
     * @param limit
     * @return
     */
    PageInfo<PositionCompanyBO> listPosition(int categoryId, int page, int limit);

    /**
     * 根据职位Id查找返回职位
     *
     * @param positionId
     * @return
     */
    PositionEntity getPositionById(int positionId);

    /**
     * 根据hrid查询返回职位
     *
     * @param hrid
     * @return
     */
    PageInfo<PositionEntity> listPositionByHr(int hrid, int page, int limit);

    List<PositionEntity> listPositionByHr(int hrid);

    /**
     * 点击量+1
     *
     * @param positionId
     * @return
     */
    boolean updateHits(int positionId);

    /**
     * delete
     *
     * @param positionId
     */
    int deletePosition(int positionId);

    /**
     * update
     */
    int updatePosition(PositionEntity positionEntity);

    int updatePositionState(int statePub, int posId);

    int savePosition(PositionEntity positionEntity);

}
