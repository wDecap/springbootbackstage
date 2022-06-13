package com.atdecap.springbootbackstage.service.impl;

import com.atdecap.springbootbackstage.entity.Article;
import com.atdecap.springbootbackstage.mapper.ArticleMapper;
import com.atdecap.springbootbackstage.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Decap
 * @since 2022-06-12
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
