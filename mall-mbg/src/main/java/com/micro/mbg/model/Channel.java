package com.micro.mbg.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "channel")
public class Channel {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String name;

    private String url;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "sort_order")
    private Integer sortOrder;
}