package qdu.java.recruit.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.CategoryEntity;
import qdu.java.recruit.mapper.CategoryMapper;
import qdu.java.recruit.service.CategoryService;

import javax.annotation.Resource;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 通过categoryId查询返回分类
     * @param categoryId
     * @return
     */
    @Override
    public CategoryEntity getCategory(int categoryId){

        return categoryMapper.getCategory(categoryId);
    }


}
