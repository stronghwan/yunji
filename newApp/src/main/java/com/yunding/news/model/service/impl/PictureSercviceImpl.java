package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.Pictures;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/27-15-31
 */
public class PictureSercviceImpl extends CommonServiceImpl<Pictures>{
    @Override
    public int save(Pictures pictures) {
        return DaoFactory.getDao("picture").save(pictures);
    }

    @Override
    public Pictures findByUserIdSingle(int id) {
        return (Pictures) DaoFactory.getDao("picture").findByUserIdSingle(id);
    }
}
