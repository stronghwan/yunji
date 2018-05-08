package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.pojo.Likes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 点赞实现类
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/24-23-33
 */
public class LikeDaoImpl extends CommonDaoImpl<Likes>{
    @Override
    public int save(final Likes likes) {
        String sql = "insert into likes(user_id,l_status,userself_name,userby_name,f_id) values" +
                "(?,?,?,?,?)";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,likes.getUserId());
                    pstmt.setInt(2,likes.getStatus());
                    pstmt.setString(3,likes.getUserSelfName());
                    pstmt.setString(4,likes.getUserByName());
                    pstmt.setInt(5,likes.getfId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public Likes findByUserIdSingle(final int id) {
        String sql = "select * from likes where f_id = ?";
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

    @Override
    public List<String> findUserNameByFId(final int id) {
        String sql = "select userself_name from likes where f_id = ?";
        return JdbcTemplate.query(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        },createHandles());
    }

    private JdbcTemplate.RowCallBackHandle<String> createHandles() {
        return new JdbcTemplate.RowCallBackHandle<String>() {
            @Override
            public String processRow(ResultSet rs) {
                String name = null;
                try {
                    name = rs.getString("user_name");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return name;
            }
        };
    }
}
