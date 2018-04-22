package com.yunding.news.model.dao;

import com.yunding.news.model.dao.impl.*;

import java.util.WeakHashMap;

/**
 * @dao工厂
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/9-12-30
 */
public final class DaoFactory {
    private static WeakHashMap<String, ICommonDao> map = new WeakHashMap<String, ICommonDao>();
    public static ICommonDao getDao(String name){
        ICommonDao dao = map.get(name);
        if(dao != null){
            return dao;
        }
        return createDao(name);
    }
// haowan
    private synchronized static ICommonDao createDao(String name) {
        ICommonDao dao = null;
        if("user".equals(name)){
            dao = new AccountDaoImpl();
            map.put(name,dao);
        }
        if("personalCenter".equals(name)){
            dao = new PersonalCenterDaoImpl();
            map.put(name,dao);
        }
        if("attention".equals(name)){
            dao = new AttentionDaoImpl();
            map.put(name,dao);
        }
        if("fc".equals(name)){
            dao = new FriendCircleDaoImpl();
            map.put(name,dao);
        }
        if("comment".equals(name)){
            dao = new CommentDaoImpl();
            map.put(name,dao);
        }
        if("fcMax".equals(name)){
            dao = new CommentFriendDaoImpl();
            map.put(name,dao);
        }
        return dao;
    }
}
