package com.atdecap.springbootbackstage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.atdecap.springbootbackstage.common.Constants;
import com.atdecap.springbootbackstage.controller.dto.UserDTO;
import com.atdecap.springbootbackstage.controller.dto.UserPasswordDTO;
import com.atdecap.springbootbackstage.entity.Menu;
import com.atdecap.springbootbackstage.entity.RoleMenu;
import com.atdecap.springbootbackstage.entity.User;
import com.atdecap.springbootbackstage.excepction.ServiceException;
import com.atdecap.springbootbackstage.mapper.RoleMapper;
import com.atdecap.springbootbackstage.mapper.RoleMenuMapper;
import com.atdecap.springbootbackstage.mapper.UserMapper;
import com.atdecap.springbootbackstage.service.IMenuService;
import com.atdecap.springbootbackstage.service.IUserService;
import com.atdecap.springbootbackstage.utils.TokenUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private static final Log LOG = Log.get();

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);

            String role = one.getRole(); //查出来是字符串 ROLE_ADMIN
            //设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            save(one); // 把 copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {

    }

    @Override
    //, String email, String address
    public Page<User> findPage(Page<User> page, String username) {
        return userMapper.findPage(page, username);
    }


    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
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
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    /**
     * 获取当前角色的菜单列表
     *
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);//通过字符串拿roleid
        //当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);//通过roleid拿全部的menuids

        //查出系统的所有菜单
        List<Menu> menus = menuService.findMenus("");
        // 创建一个最后筛选完成之后的list
        List<Menu> rolemenus = new ArrayList<>();
        //筛选当前用户角色菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                rolemenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            //移除children 里面不在menuIds集合中的元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
    return rolemenus;
    }

}

