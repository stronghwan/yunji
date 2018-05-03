package com.yunding.news.web.others.javaBean;

import com.yunding.news.web.others.jdbc.Permissions;
import com.yunding.news.web.others.jdbc.Delete;
import com.yunding.news.web.others.jdbc.Query;

import java.sql.SQLException;
import java.util.List;

public class JavaBean {

    //权限表单信息增加（新注册用户对应）!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void add(String user_name) throws SQLException {
        Permissions permissions = new Permissions();
        permissions.addUserPer(permissions.getUserId(user_name));
    }


    //通过username查询userid
    public int queryID(String user_name) throws SQLException {
        Permissions permissions = new Permissions();
        int user_id = permissions.getUserId(user_name);
        return user_id;
    }


    //通过username批量查询userid
    public int[] queryIDs(String[] user_names) throws SQLException {
        int[] ids = null;
        for(int i = 0; i < user_names.length; i++){
            ids[i] = queryID(user_names[i]);
        }
        return ids;
    }


    //用户权限的更改
    public void upDate(String[] isNo,String user_name) throws SQLException {
        int id = queryID(user_name);
        isNo[isNo.length] = String.valueOf(id);
        Permissions permissions = new Permissions();
        permissions.update(isNo);
    }


    //通过id查询用户权限信息用于权限设置!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public String[] getPer(int user_id) throws SQLException {
        Permissions permissions = new Permissions();
        return permissions.getPer(user_id);
    }


    //用户信息的删除
    public void delete(int id){
        Delete delete = new Delete();
        delete.inforDelete(id);
    }


    //批量删除
    public void batchDelete(int[] user_ids){
        Delete delete = new Delete();
        for(int i = 0; i < user_ids.length; i++) {
            delete.inforDelete(user_ids[i]);
        }
    }


    //管理系统用户的查询
    public List query(){
        Query query = new Query();
        return query.inforQuery();
    }



//    //主函数测试
//    public static void main(String[] args){
//        JavaBean javaBean = new JavaBean();
//        try {
//            javaBean.add("ccc");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
