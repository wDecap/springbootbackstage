package com.atdecap.springbootbackstage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Decap
 * @since 2022-05-24
 */
@Getter
@Setter
@TableName("role")
@ApiModel(value = "Role对象", description = "")
public class Role implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("id")
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @ApiModelProperty("名称")
  private String name;

  @ApiModelProperty("描述")
  private String description;

  @ApiModelProperty("唯一标识")
  private String flag;

}
