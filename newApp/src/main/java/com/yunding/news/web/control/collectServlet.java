package com.yunding.news.web.control;
/*
 * @Name:朋友圈•收藏
 * @Author:Farmerzhang
 * @Date: 2018/5/1
 * @Time: 19:34
 */

import com.yunding.news.model.pojo.Collect;
import com.yunding.news.model.service.ServiceFactory;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.yunding.news.model.service.ServiceFactory.getService;

public class collectServlet extends HttpServlet {


    public collectServlet() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
      //解析JSON数据
        String json="";
        JSONArray jsonArray=JSONArray.fromObject(json);
        Collect collect=null;
        for (int i=0;i<jsonArray.size();i++) {
            collect=new Collect();
            String fCreatetime = jsonArray.getJSONObject(i).getString("fcreate_time");
            java.sql.Date fdate = null;  //格式
            DateFormat sdf = new SimpleDateFormat();
            try {
                fdate = (java.sql.Date) sdf.parse(fCreatetime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            collect.setfTime(fdate);
            collect.setfContent(jsonArray.getJSONObject(i).getString("fcontent"));
            collect.setfId(jsonArray.getJSONObject(i).getInt("fid"));
            collect.setUserName(jsonArray.getJSONObject(i).getString("fuser_name"));//说说name

            String cUsername=jsonArray.getJSONObject(i).getString("cuser_name");
            int cuserid= getService( "user" ).findUserId( cUsername);//收藏人的id
            collect.setUserId(cuserid);
            collect.setUrl(jsonArray.getJSONObject(i).getString("furl"));
            ServiceFactory.getService("collect").save(collect);
        }





        for (int i=0;i<jsonArray.size();i++){
            String cUsername=jsonArray.getJSONObject(i).getString("cUsername");
        int cUser_id= getService( "user" ).findUserId(cUsername  );
        List<Collect> collects= getService("collect").findByUserId(cUser_id);
        collect_ collect_=null;
        List<collect_> collect_s=new ArrayList<>();
        for (Collect collect1:collects){
            collect_=new collect_();
            collect_.setfId(collect1.getfId());  //
            collect_.setCollId(collect1.getCollId());
            collect_.setfContent(collect1.getfContent());
            collect_.setUserName(collect1.getUserName());
            Date ftime=collect1.getfTime();
            DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH:mm");
            String fTime=sdf.format(ftime);
            collect_.setfTime(fTime);
            collect_.setUrl(collect1.getUrl());
            collect_s.add(collect_);
        }

       //给说说进行排序
        sortClass_C sortClass_c=new sortClass_C();
        Collections.sort(collect_s,sortClass_c);




        jsonArray=JSONArray.fromObject(collect_s);


        }




    }
}




