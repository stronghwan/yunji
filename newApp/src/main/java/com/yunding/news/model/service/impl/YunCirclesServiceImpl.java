package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.YunCircles;

import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/4-15-28
 */
public class YunCirclesServiceImpl extends CommonServiceImpl<YunCircles>{
    @Override
    public int save(YunCircles yunCircles) {
        return DaoFactory.getDao("yunCircles").save(yunCircles);
    }

    @Override
    public List<YunCircles> findCommon(String common) {
        return DaoFactory.getDao("yunCircles").findCommon(common);
    }
}
