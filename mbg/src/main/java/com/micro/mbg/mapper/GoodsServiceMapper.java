package com.micro.mbg.mapper;

import com.micro.mbg.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface GoodsServiceMapper {

    @Select("select id, name, primary_pic_url, price\n" +
            "from goods\n" +
            "where is_delete = 0\n" +
            "  and id = #{goodsId}")
    Goods getSimpleGoods(@Param("goodsId") Integer goodsId);

    @Select("<script>\n" +
            "select id, name, primary_pic_url, price\n" +
            "from goods\n" +
            "where is_delete = 0\n" +
            "  and id in " +
            "    <foreach item='item' collection='goodsIdList' open='(' separator=',' close=')'>\n" +
            "    #{item}\n" +
            "    </foreach>\n" +
            "</script>")
    List<Goods> getSimpleGoodsList(@Param("goodsIdList") List<Integer> goodsIdList);
}
