package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.pojo.YunLikes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/3-23-51
 */
public class YunLikesDaoImpl extends CommonDaoImpl<YunLikes>{

    @Override
    public int save(final YunLikes yunLikes) {
        String sql = "insert into yunLikes(l_status, y_id) " +
                "values(?,?)";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,yunLikes.getStatus());
                    pstmt.setInt(2,yunLikes.getyId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int modifiedUserInfo(final YunLikes yunLikes) {
        String sql = "update yunLikes set l_totalnumber = ? where y_id = ?";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,yunLikes.getTotalNumber());
                    pstmt.setInt(2,yunLikes.getyId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public YunLikes findByUserIdSingle(final int id) {
        String sql = "select * from where f_id = ?";
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

    private JdbcTemplate.RowCallBackHandle<YunLikes> createHandle() {
        return new JdbcTemplate.RowCallBackHandle<YunLikes>() {
            @Override
            public YunLikes processRow(ResultSet rs) {
                YunLikes yunLikes = null;
                try {
                    yunLikes = new YunLikes();
                    yunLikes.setlId(rs.getInt("l_id"));
                    yunLikes.setStatus(rs.getInt("l_status"));
                    yunLikes.setTotalNumber(rs.getInt("l_totalNumber"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return yunLikes;
            }
        };
    }
}
