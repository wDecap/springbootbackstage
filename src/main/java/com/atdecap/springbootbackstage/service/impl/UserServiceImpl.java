package com.atdecap.springbootbackstage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.atdecap.springbootbackstage.common.Constants;
import com.atdecap.springbootbackstage.controller.dto.UserDTO;
import com.atdecap.springbootbackstage.entity.User;
import com.atdecap.springbootbackstage.excepction.ServiceException;
import com.atdecap.springbootbackstage.mapper.UserMapper;
import com.atdecap.springbootbackstage.service.IUserService;
import com.atdecap.springbootbackstage.utils.TokenUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Decap
 * @since 2022-05-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
     private  static  final Log LOG = Log.get();
    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one,userDTO,true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }
        else {
            throw new  ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one == null){
            one = new User();
            BeanUtil.copyProperties(userDTO,one,true);
        save(one); // 把 copy完之后的用户对象存储到数据库
        }
        else{
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
    return one;
    }
    private User getUserInfo(UserDTO userDTO){QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        //      User one = getOne(queryWrapper);
//        if (one != null) {
//            return true; 会有重复脏数据
//        }
        //     return false;
//    }
        /*    List<User> list = list(queryWrapper);
       return list.size() != 0;*/
        User one;
        try {
            one = getOne(queryWrapper);//从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new  ServiceException(Constants.CODE_500,"系统错误");
        }
   return one;
    }
}

