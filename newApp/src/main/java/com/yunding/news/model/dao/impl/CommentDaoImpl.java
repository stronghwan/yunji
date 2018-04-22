package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.pojo.Comment;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/20-15-02
 */
public class CommentDaoImpl extends CommonDaoImpl<Comment>{
    /**
     * 添加评论
     * @param comment  评论的对象
     * @return
     */
    @Override
    public int save(final Comment comment) {
        String sql = "insert into comments(user_id,c_content,c_time) values(?,?,?)";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1, comment.getcUserId());
                    pstmt.setString(2, comment.getcContent());
                    pstmt.setDate(3, (Date) comment.getC_time());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public Comment findByUserId(final int id) {
        String sql = "select * from comments where user_id = ?";
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

    private JdbcTemplate.RowCallBackHandle<Comment> createHandle() {
        return new JdbcTemplate.RowCallBackHandle<Comment>() {
            @Override
            public Comment processRow(ResultSet rs) {
                Comment comment = null;
                try {
                    comment.setcId(rs.getInt("c_id"));
                    comment.setcUserId(rs.getInt("user_id"));
                    comment.setC_time(rs.getDate("c_time"));
                    comment.setcContent(rs.getString("c_content"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return comment;
            }
        };
    }
}
