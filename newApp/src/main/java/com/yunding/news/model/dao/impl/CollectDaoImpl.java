package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.pojo.Collect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @收藏实现类
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/1-14-21
 */
public class CollectDaoImpl extends CommonDaoImpl<Collect>{
    @Override
    public int save(final Collect collect) {
        String sql = "insert into collects(user_id,f_id,f_time,f_content,user_name,p_url) values" +
                "(?,?,?,?,?,?)";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,collect.getUserId());
                    pstmt.setInt(2,collect.getfId());
                    pstmt.setDate(3,collect.getfTime());
                    pstmt.setString(4,collect.getfContent());
                    pstmt.setString(5,collect.getUserName());
                    pstmt.setString(6,collect.getUrl());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public List<Collect> findByUserId(final int id) {
        String sql = "select * from collects where user_id = ?";
        return JdbcTemplate.query(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        },createHandle());
    }

    private JdbcTemplate.RowCallBackHandle<Collect> createHandle() {
        return new JdbcTemplate.RowCallBackHandle<Collect>() {
            @Override
            public Collect processRow(ResultSet rs) {
                Collect collect = null;
                try {
                    collect = new Collect();
                    collect.setCollId(rs.getInt("coll_id"));
                    collect.setfContent(rs.getString("f_content"));
                    collect.setfId(rs.getInt("f_id"));
                    collect.setfTime(rs.getDate("f_time"));
                    collect.setUserId(rs.getInt("user_id"));
                    collect.setUserName(rs.getString("user_name"));
                    collect.setUrl(rs.getString("p_url"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return collect;
            }
        };
    }
}
