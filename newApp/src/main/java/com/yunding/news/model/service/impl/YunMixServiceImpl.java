package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.YunMix;

import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/4-15-33
 */
public class YunMixServiceImpl extends CommonServiceImpl<YunMix> {
    @Override
    public List<YunMix> findCommon(String common) {
        return DaoFactory.getDao("yMix").findCommon(common);
    }
}
