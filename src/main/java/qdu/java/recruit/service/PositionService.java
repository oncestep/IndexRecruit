package qdu.java.recruit.service;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.Position;
import qdu.java.recruit.entity.User;


public interface PositionService {

    /**
     * 得到针对当前用户推荐列表
     * @param user
     * @return
     */
    PageInfo<Position> recPosition(User user,int page,int limit);


}
