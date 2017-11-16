package qdu.java.recruit.service;

import qdu.java.recruit.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    /**
     * 通过categoryId查询返回分类
     * @param categoryId
     * @return
     */
    CategoryEntity getCategory(int categoryId);

    List<CategoryEntity> getAll();
}
