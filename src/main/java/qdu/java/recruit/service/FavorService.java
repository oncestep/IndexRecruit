package qdu.java.recruit.service;

import qdu.java.recruit.pojo.FavorPositionBO;

import java.util.List;

public interface FavorService {

    List<FavorPositionBO> listFavorPosition(int userId);

    boolean favorPosition(int userId,int posId);

    boolean favorOrNot(int userId, int posId);

    boolean disfavorPosition(int userId, int posId);
}
