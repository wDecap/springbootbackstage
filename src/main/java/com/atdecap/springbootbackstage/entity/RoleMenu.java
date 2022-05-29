package com.atdecap.springbootbackstage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.entity
 * @create 2022-05-2022/5/2621:46
 * @desc
 **/
@TableName("role_menu")
@Data
public class RoleMenu {

    private Integer roleId;
    private Integer menuId;

}
