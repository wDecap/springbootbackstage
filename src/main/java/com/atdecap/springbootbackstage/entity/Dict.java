package com.atdecap.springbootbackstage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.entity
 * @create 2022-05-2022/5/2621:51
 * @desc 选图标的
 **/
@TableName("dict")
@Data
public class Dict {

    private String name;
    private String value;
    private String type;

}
