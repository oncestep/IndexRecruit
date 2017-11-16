package qdu.java.recruit.service.impl;

import org.springframework.stereotype.Service;
import qdu.java.recruit.mapper.FavorMapper;
import qdu.java.recruit.pojo.FavorPositionBO;
import qdu.java.recruit.service.FavorService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FavorServiceImpl implements FavorService {

    @Resource
    private FavorMapper favorMapper;

    @Override
    public List<FavorPositionBO> listFavorPosition(int userId) {

        return favorMapper.listFavorPosition(userId);
    }

    @Override
    public boolean favorPosition(int userId, int posId) {

        if (favorMapper.saveFavor(userId, posId) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean favorOrNot(int userId, int posId) {

        if (favorMapper.getFavor(userId, posId) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean disfavorPosition(int userId, int posId) {

        if (favorMapper.removeFavor(userId, posId) > 0) {
            return true;
        } else {
            return false;
        }
    }
}
