package com.atdecap.springbootbackstage.service.impl;

import com.atdecap.springbootbackstage.entity.User;
import com.atdecap.springbootbackstage.mapper.UserMapper;
import com.atdecap.springbootbackstage.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Decap
 * @since 2022-05-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
