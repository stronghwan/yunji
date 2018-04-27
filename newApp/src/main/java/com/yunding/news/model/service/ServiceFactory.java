package com.yunding.news.model.service;

import com.yunding.news.model.service.impl.*;

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
            map.put(name,service);
        }
        if("personalCenter".equals(name)){
            service = new PersonalCenterServiceImpl();
            map.put(name,service);
        }
        if("attention".equals(name)){
            service = new AttentionServiceImpl();
            map.put(name,service);
        }
        if("fc".equals(name)){
            service = new FriendCircleServiceImpl();
            map.put(name,service);
        }
        if("comment".equals(name)){
            service = new CommentServiceImpl();
            map.put(name,service);
        }
        if("fcMix".equals(name)){
            service = new CommentFriendServiceImpl();
            map.put(name,service);
        }
        if("like".equals(name)){
            service = new LikesServiceImpl();
        }
        if("picture".equals(name)){
            service = new PictureSercviceImpl();
            map.put(name,service);
        }
        return service;
    }
}
