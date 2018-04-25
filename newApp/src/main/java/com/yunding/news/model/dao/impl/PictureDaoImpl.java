package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.pojo.Pictures;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/25-00-05
 */
public class PictureDaoImpl extends CommonDaoImpl<Pictures>{
    @Override
    public int save(final Pictures pictures) {
        String sql = "insert into pictures(user_id,p_url,user_name) values" +
                "(?,?,?)";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,pictures.getUserId());
                    pstmt.setString(2,pictures.getUrl());
                    pstmt.setString(3,pictures.getUserName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public Pictures findByUserId(final int id) {
        String sql = "select * from pictures where user_id = ?";
        return JdbcTemplate.SingleQuery(sql, new JdbcTemplate.PreparedStatementSetter() {
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

    private JdbcTemplate.RowCallBackHandle<Pictures> createHandle() {
        return new JdbcTemplate.RowCallBackHandle<Pictures>() {
            @Override
            public Pictures processRow(ResultSet rs) {
                Pictures pictures = null;
                try {
                    pictures = new Pictures();
                    pictures.setpId(rs.getInt("p_id"));
                    pictures.setpId(rs.getInt("user_name"));
                    pictures.setUserName(rs.getString("p_url"));
                    pictures.setUserName(rs.getString("user_name"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return pictures;
            }
        };
    }
}
