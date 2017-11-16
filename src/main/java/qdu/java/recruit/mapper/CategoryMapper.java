package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.entity.CategoryEntity;

import java.util.List;

public interface CategoryMapper {

    @Select("select * from category where categoryId = #{categoryId}")
    CategoryEntity getCategory(@Param("categoryId") int categoryId);

    @Select("select * from category")
    List<CategoryEntity> getAll();
}
