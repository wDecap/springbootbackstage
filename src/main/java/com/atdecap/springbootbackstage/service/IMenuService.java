package com.atdecap.springbootbackstage.service;

import com.atdecap.springbootbackstage.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Decap
 * @since 2022-05-26
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
