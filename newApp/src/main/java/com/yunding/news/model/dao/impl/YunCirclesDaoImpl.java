package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.YunCircles;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/3-23-50
 */
public class YunCirclesDaoImpl extends CommonDaoImpl<YunCircles>{

    @Override
    public int save(final YunCircles yunCircles) {
        String sql = "insert into yunCircles(user_id, user_name, y_content, kindOf)" +
                "values(?,?,?,?)";
        final int id = DaoFactory.getDao("user").findUserIdPersonal(yunCircles.getUserName());
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,id);
                    pstmt.setString(2,yunCircles.getUserName());
                    pstmt.setString(3,yunCircles.getyContent());
                    pstmt.setString(4,yunCircles.getKindof());
                    pstmt.setDate(5,yunCircles.getY_time());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public List<YunCircles> findCommon(final String common) {
        String sql = "select * from yunCircles where kindOf = ?";
        return JdbcTemplate.query(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,common);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        },createHandle());
    }

    private JdbcTemplate.RowCallBackHandle<YunCircles> createHandle() {
        return new JdbcTemplate.RowCallBackHandle<YunCircles>() {
            @Override
            public YunCircles processRow(ResultSet rs) {
                YunCircles yunCircles = null;
                try {
                    yunCircles = new YunCircles();
                    yunCircles.setyId(rs.getInt("y_id"));
                    yunCircles.setKindof(rs.getString("kindOf"));
                    yunCircles.setUserName(rs.getString("user_name"));
                    yunCircles.setUserId(rs.getInt("user_id"));
                    yunCircles.setyContent(rs.getString("y_content"));
                    yunCircles.setY_time(rs.getDate("y_time"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return yunCircles;
            }
        };
    }
}
