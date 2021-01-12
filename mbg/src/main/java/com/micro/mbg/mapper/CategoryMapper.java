package com.micro.mbg.mapper;

import com.micro.mbg.model.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryMapper extends Mapper<Category> {
    /**
     * 查找所有主分类
     * @return
     */
    @Select("select id,name from category where parent_id = 0 order by sort_order asc")
    List<Category> findMainCategory();

    /**
     * 查找父分类下的所有子分类
     * @param parentId
     * @return
     */
    @Select("select id,name,icon_url from category where parent_id = #{parentId} order by sort_order asc")
    List<Category> findSubCategory(@Param("parentId") int parentId);


    /**
     * 查找同一父分类下的兄弟分类
     * @param id
     * @return
     */
    @Select("select id, name\n" +
            "from category\n" +
            "where parent_id = (select parent_id from category where id = #{id})\n" +
            "order by sort_order asc")
    List<Category> findBrotherCategory(@Param("id") int id);



}
