package com.atdecap.springbootbackstage.service;

import com.atdecap.springbootbackstage.entity.User;
import com.atdecap.springbootbackstage.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Decap
 * @BelongsProject: IntelliJ IDEA
 * @BelongsPackage: com.atdecap.springbootbackstage.service
 * @create 2022-04-2022/4/1911:29
 * @desc server层
 **/
@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    public boolean saveUser(User user) {
//        if(user.getId()!=null){
//            save(user);//mybatis-plus提供的方法
//        }else {
//            return updateById(user);
//        }
//        return false;
        boolean b = saveOrUpdate(user); //saveOrUpdata是mybatis-plus的注解
        return b;
    } //首先要到UserMapper下继承下BaseMapper 不然爆红


    //    @Autowired
//    private UserMapper userMapper;
//    public int save(User user){
//        if (user.getId() == null) {//user没有id，则表示是新增
//          return userMapper.insert(user);
//        }else {//否则为更新
//         return userMapper.update(user);
//        }
//    }

}
