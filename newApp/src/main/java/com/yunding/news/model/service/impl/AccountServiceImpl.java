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
public class AccountServiceImpl extends CommonDaoImpl<Account>{
    @Override
    public int save(Account account) {
        return DaoFactory.getDao("user").save(account);
    }

    @Override
    public Account findByUserName(String name) {
        return (Account) DaoFactory.getDao("user").findByUserName(name);
    }
}
