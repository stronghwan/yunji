package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.PersonalCenter;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/10-07-21
 */
public class PersonalCenterServiceImpl extends CommonServiceImpl<PersonalCenter>{

    @Override
    public int save(PersonalCenter personalCenter) {
        return DaoFactory.getDao("personalCenter").save(personalCenter);
    }

    @Override
    public PersonalCenter findByPerUserID(String name) {
        return (PersonalCenter) DaoFactory.getDao("personalCenter").findByPerUserID(name);
    }

    @Override
    public int modifiedUserInfo(PersonalCenter personalCenter) {
        return DaoFactory.getDao("user").modifiedUserInfo(personalCenter);
    }
}
