package com.micro.mbg.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "ad")
public class Ad {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    private String link;

    @Column(name = "sort_order")
    private Integer sortOrder;

    private Boolean enable;

    private Date create;
}