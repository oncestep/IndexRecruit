package qdu.java.recruit.pojo;

import qdu.java.recruit.entity.PositionEntity;

public class FavorPositionBO extends PositionEntity {

    private int favorId;
    private int userId;

    public int getFavorId() {
        return favorId;
    }

    public void setFavorId(int favorId) {
        this.favorId = favorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
