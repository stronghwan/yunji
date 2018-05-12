package com.yunding.news.model.dao.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.*;

import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/4-14-07
 */
public class YunMixDaoImpl extends CommonDaoImpl<YunMix>{
    @Override
    public List<YunMix> findCommon(String common) {
        List<YunMix> lYMix = null;
        List<YunCircles> lYCircle
                = DaoFactory.getDao("yunCircles").findCommon(common);
        List<YunComments> lYComments = null;
        YunMix yunMix = null;
        yunMix.setlYCircles(lYCircle);
        for (YunCircles yunCircles:lYCircle) {
            yunMix = new YunMix();
            lYComments = DaoFactory.getDao("yunComments").findByUserId(yunCircles.getyId());
            yunMix.setlYComments(lYComments);
            YunLikes yunLikes = (YunLikes) DaoFactory.getDao("yunLikes").findByUserIdSingle(yunCircles.getyId());
            Account account = (Account) DaoFactory.getDao("user").findByUserName(yunCircles.getUserName());
            yunMix.setNickName(account.getNickName());
            yunMix.setlStatus(yunLikes.getStatus());
            yunMix.setTotalNumber(yunLikes.getTotalNumber());
            lYMix.add(yunMix);
        }
        return lYMix;
    }
}
