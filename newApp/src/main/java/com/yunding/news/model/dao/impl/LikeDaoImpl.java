package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.pojo.Likes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 点赞实现类
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/24-23-33
 */
public class LikeDaoImpl extends CommonDaoImpl<Likes>{
    @Override
    public int save(final Likes likes) {
        String sql = "insert into likes(user_id,l_status,userself_name,userby_name) values" +
                "(?,?,?,?)";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,likes.getUserId());
                    pstmt.setInt(2,likes.getStatus());
                    pstmt.setString(3,likes.getUserSelfName());
                    pstmt.setString(4,likes.getUserByName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public Likes findByUserId(final int id) {
        String sql = "select * from likes where user_id = ?";
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

    private JdbcTemplate.RowCallBackHandle<Likes> createHandle() {
        return new JdbcTemplate.RowCallBackHandle<Likes>() {
            @Override
            public Likes processRow(ResultSet rs) {
                Likes likes = null;
                try {
                    likes = new Likes();
                    likes.setlId(rs.getInt("l_id"));
                    likes.setStatus(rs.getInt("l_status"));
                    likes.setUserId(rs.getInt("user_id"));
                    likes.setUserSelfName(rs.getString("userself_name"));
                    likes.setUserByName(rs.getString("userby_name"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return likes;
            }
        };
    }
}