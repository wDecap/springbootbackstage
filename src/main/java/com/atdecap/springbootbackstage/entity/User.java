package com.atdecap.springbootbackstage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.entity
 * @create 2022-04-2022/4/1816:03
 * @desc 实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User {
    @TableId(type = IdType.AUTO,value = "id")
private Integer id;
private String username;
@JsonIgnore
private String password;
private String email;
@TableField(value = "telephone")
private String phone;
private String address;
private String integration;
@TableField(value = "avatar_url") //指定数据库的字段名称     也可以用驼峰命名 avatarUrl
private String avatar;

}
