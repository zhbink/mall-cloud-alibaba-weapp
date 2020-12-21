package com.micro.mbg.mapper;


import com.micro.mbg.model.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SearchMapper {

    /**
     * 热度算法
     * Score = (1*click + 10 * want + 500) /e^ (day/10)
     * = (1*click + 10 * want + 500) / e^((T(now) - T *  10^-7 )
     */
//    String popular_score = "(1 * browse_count + 10 * want_count + 500) / exp((now() - last_edit) * POW(10, -7))";
//
//
//    @Select("select keyword\n" +
//            "from search_history\n" +
//            "where user_id = #{user_id}\n" +
//            "order by search_time desc")
//    List<SearchHistory> findSearchHistory(@Param("user_id") String userId);


    /**
     * 通过关键字列表搜索商品
     * @param keywords
     * @return
     */
    @Select("<script>\n" +
            "select id, `name`, primary_pic_url, price\n" +
            "from goods\n" +
            "where\n" +
            "    <foreach item='item' collection='keywords' open='(' separator='or' close=')'>\n" +
            "    name like concat(concat('%', #{item}, '%'))\n" +
            "    </foreach>\n" +
            "  and is_selling = 1\n" +
            "  and is_delete = 0\n" +
            "</script>")
    List<Goods> findGoodsByKeywords(@Param("keywords") List<String> keywords);


//    @Delete("delete from search_history where user_id = #{user_id}")
//    void clearHistory(@Param("user_id") String userId);
//
//    @Delete("delete\n" +
//            "from search_history\n" +
//            "where id in (select id from (select id from search_history where user_id = #{user_id} order by search_time asc limit #{limit}) as tmp)")
//    void deleteOldHistory(@Param("user_id") String userId, @Param("limit") int limit);
//
//    /**
//     * 之前是否搜索过该关键字
//     * @param userId
//     * @param keyword
//     * @return
//     */
//    @Select("SELECT EXISTS(SELECT 1 FROM search_history WHERE user_id = #{user_id}\n" +
//            "                                             and keyword = #{keyword})")
//    Boolean isExistedHistory(@Param("user_id") String userId, @Param("keyword") String keyword);
//
//    @Insert("insert into search_history (user_id, keyword) VALUES (#{user_id}, #{keyword})")
//    void insertHistory(@Param("user_id") String userId, @Param("keyword") String keyword);
//
//
//    @Update("update search_history\n" +
//            "set search_time = now()\n" +
//            "where user_id = #{user_id} and keyword = #{keyword}")
//    void updateSearchTime(@Param("user_id") String userId, @Param("keyword") String keyword);


}