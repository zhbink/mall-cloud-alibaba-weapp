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
@Table(name = "region")
public class Region {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Short id;

    @Column(name = "parent_id")
    private Short parentId;

    private String name;

    private Boolean type;

    @Column(name = "agency_id")
    private Short agencyId;
}