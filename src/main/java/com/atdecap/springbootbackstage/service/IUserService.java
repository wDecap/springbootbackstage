package com.atdecap.springbootbackstage.service;

import com.atdecap.springbootbackstage.controller.dto.UserDTO;
import com.atdecap.springbootbackstage.controller.dto.UserPasswordDTO;
import com.atdecap.springbootbackstage.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Decap
 * @since 2022-05-03
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);

    //, String email, String address
    Page<User> findPage(Page<User> objectPage, String username);
}
