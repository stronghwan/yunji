package com.yunding.news.common;

import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/2-20-58
 */
public final class JdbcTemplate {
    /**
     * 发送sql语句接口
     */
    public static interface PreparedStatementSetter{
        void setValues(PreparedStatement pstmt);
    }

    /**
     * 封装数据库返回的内容
     * @param <T>
     */
    public static interface RowCallBackHandle<T>{
        T processRow(ResultSet rs);
    }

    /**
     * 单个查询
     * @param sql
     * @param setter
     * @param handle
     * @param <T>
     * @return
     */

    public static <T> T SingleQuery(String sql, PreparedStatementSetter setter, RowCallBackHandle<T> handle){
        ResultSet rs = null;
        try {
            rs = query(sql,setter);
            if(handle != null && rs.next()){
              return handle.processRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DBUtil.release(rs);
        }
        return null;
    }

    /**
     * 查询全部
     * @param sql
     * @param setter
     * @param handle
     * @param <T>
     * @return
     */

    public static <T> List<T> query(String sql, PreparedStatementSetter setter, RowCallBackHandle<T> handle){
        ResultSet rs = null;
        List<T> list =  null;
        try {
            rs = query(sql,setter);
            if(handle != null){
              list = new ArrayList<T>();
                while ( rs.next()){
                    list.add( handle.processRow(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DBUtil.release(rs);
        }
        return list;
    }

    /**
     *
     * @param sql
     * @param setter
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static ResultSet query(String sql, PreparedStatementSetter setter) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = (PreparedStatement) ConnectionManager.getConnection().prepareStatement(sql);
        // 匿名内部类
        if(setter != null){
            setter.setValues(pstmt);
        }
        return pstmt.executeQuery();
    }

    /**
     * 更新
     * @param sql
     * @param setter
     * @return
     */
    public static int update(String sql, PreparedStatementSetter setter){
        PreparedStatement pstmt = null;
        try {
            pstmt = ConnectionManager.getConnection().prepareStatement(sql);
            if (setter != null){
                setter.setValues(pstmt);
            }
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            DBUtil.release(pstmt);
        }
        return 0;
    }

}
