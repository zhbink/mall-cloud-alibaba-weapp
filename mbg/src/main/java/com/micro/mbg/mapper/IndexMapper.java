package com.micro.mbg.mapper;

import com.micro.mbg.model.Ad;
import com.micro.mbg.model.Channel;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface IndexMapper {

    @Select("select id,name,url,icon_url from channel order by sort_order asc")
    List<Channel> findChannel();

    @Select("select id, image_url, link\n" +
            "from ad\n" +
            "where enable = true\n" +
            "order by sort_order asc, `create` desc\n" +
            "limit 5")
    List<Ad> findAd();
}
