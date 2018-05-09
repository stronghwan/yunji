package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.pojo.YunComments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/3-23-51
 */
public class YunCommentsDaoImpl extends CommonDaoImpl<YunComments>{

    @Override
    public int save(final YunComments yunComments) {
        String sql = "insert into yunComments(user_id,f_id, user_name,c_content) " +
                "values(?,?,?,?)";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,yunComments.getUserId());
                    pstmt.setInt(2,yunComments.getfId());
                    pstmt.setString(3,yunComments.getUserName());
                    pstmt.setString(4,yunComments.getUserName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public List<YunComments> findByUserId(final int id) {
        String sql = "select * from yunComments where f_id = ?";
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

    private JdbcTemplate.RowCallBackHandle<YunComments> createHandle() {
        return new JdbcTemplate.RowCallBackHandle<YunComments>() {
            @Override
            public YunComments processRow(ResultSet rs) {
                YunComments yunComments = null;
                try {
                    yunComments = new YunComments();
                    yunComments.setcId(rs.getInt("c_id"));
                    yunComments.setfId(rs.getInt("f_id"));
                    yunComments.setUserId(rs.getInt("user_id"));
                    yunComments.setUserName(rs.getString("user_name"));
                    yunComments.setcContent(rs.getString("c_content"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return yunComments;
            }
        };
    }
}
