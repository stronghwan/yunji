package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.Attention;
import com.yunding.news.model.service.ICommonService;

import java.io.Serializable;
import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/18-21-52
 */
public class AttentionServiceImpl extends CommonServiceImpl<Attention>{
    @Override
    public List<Attention> findAttByUserId() {
        return DaoFactory.getDao("attention").findAttByUserId();
    }

    @Override
    public int save(Attention attention) {
        return DaoFactory.getDao("attention").save(attention);
    }
}
