package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.Account;
import com.yunding.news.model.pojo.Comment;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        String sql = "insert into comments(user_id,c_content,c_time,f_id,use_name,nickName) " +
                "values(?,?,?,?,?,?)";
        Account account = (Account) DaoFactory.getDao("user").findByUserName(comment.getUserName());
        comment.setNickName(account.getNickName());
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1, comment.getcUserId());
                    pstmt.setString(2, comment.getcContent());
                    pstmt.setDate(3, (Date) comment.getC_time());
                    pstmt.setInt(4,comment.getfId());
                    pstmt.setString(5,comment.getUserName());
                    pstmt.setString(6,comment.getNickName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public List<Comment> findByUserId(final int id) {
        String sql = "select * from comments where f_id = ?";
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

    private JdbcTemplate.RowCallBackHandle<Comment> createHandle() {
        return new JdbcTemplate.RowCallBackHandle<Comment>() {
            @Override
            public Comment processRow(ResultSet rs) {
                Comment comment = null;
                try {
                    comment = new Comment();
                    comment.setcId(rs.getInt("c_id"));
                    comment.setcUserId(rs.getInt("user_id"));
                    comment.setC_time(rs.getDate("c_time"));
                    comment.setcContent(rs.getString("c_content"));
                    comment.setUserName(rs.getString("user_name"));
                    comment.setNickName(rs.getString("nickName"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return comment;
            }
        };
    }
}
