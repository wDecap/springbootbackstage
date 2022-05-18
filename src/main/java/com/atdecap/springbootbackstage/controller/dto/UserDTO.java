package com.atdecap.springbootbackstage.controller.dto;

import lombok.Data;

/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.controller.dto
 * @create 2022-05-2022/5/1022:37
 * @desc 接收前端登入请求的参数
 **/
@Data
public class UserDTO {
    private String username;
    private String password;
    private String avatarUrl;
    private String nickname;
    private String token;
}
