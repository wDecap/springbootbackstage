package com.atdecap.springbootbackstage.controller.dto;

import lombok.Data;

/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.controller.dto
 * @create 2022-06-2022/6/121:10
 * @desc
 **/
@Data
public class UserPasswordDTO {
    private String username;
    private String password;
    private String newPassword;
}
