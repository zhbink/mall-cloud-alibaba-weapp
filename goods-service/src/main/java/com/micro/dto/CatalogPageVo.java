package com.micro.dto;

import com.micro.mbg.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogPageVo {

    /*其他所有和这个同级的分类*/
    private List<Category> allCategory;

    /*这个分类的所有子分类*/
    private List<Category> subCategory;
}
