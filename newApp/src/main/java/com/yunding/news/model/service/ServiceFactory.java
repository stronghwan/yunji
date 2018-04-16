package com.yunding.news.model.service;

import com.yunding.news.model.service.impl.AccountServiceImpl;
import com.yunding.news.model.service.impl.PersonalCenterServiceImpl;

import javax.swing.*;
import java.util.WeakHashMap;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/16-16-07
 */
public final class ServiceFactory {
    private static WeakHashMap<String,ICommonService> map = new WeakHashMap<String, ICommonService>();
    public static ICommonService getService(String name){
        ICommonService service = map.get(name);
        if(service != null){
            return service;
        }
        return createService(name);
    }

    private static ICommonService createService(String name) {
        ICommonService service = null;
        if("user".equals(name)){
            service = new AccountServiceImpl();
        }
        if("personalCenter".equals(name)){
            service = new PersonalCenterServiceImpl();
        }
        return service;
    }
}
