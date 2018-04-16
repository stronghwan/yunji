package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.dao.impl.CommonDaoImpl;
import com.yunding.news.model.pojo.Account;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/9-12-48
 */
public class AccountServiceImpl extends CommonServiceImpl<Account>{
    @Override
    public int save(Account account) {
        return DaoFactory.getDao("user").save(account);
    }

    @Override
    public Account findByUserName(String name) {
        return (Account) DaoFactory.getDao("user").findByUserName(name);
    }

    @Override
    public int saveByStep(Account account) {
        return DaoFactory.getDao("user").saveByStep(account);
    }

    @Override
    public int saveByStepTwo(Account account) {
        return DaoFactory.getDao("user").saveByStepTwo(account);
    }

    @Override
    public int saveByStepThree(Account account) {
        return DaoFactory.getDao("user").saveByStepThree(account);
    }

    @Override
    public int modifiedUserInfo(Account account) {
        return DaoFactory.getDao("user").modifiedUserInfo(account);
    }

    @Override
    public Account findByPerUserID(String name) {
        return (Account) DaoFactory.getDao("user").findByPerUserID(name);
    }

    @Override
    public int findUserId(String name) {
        return DaoFactory.getDao("user").findUserId(name);
    }
}
