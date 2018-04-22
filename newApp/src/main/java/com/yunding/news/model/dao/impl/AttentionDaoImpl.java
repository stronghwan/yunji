package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.Attention;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @关注实现方法
 * attention.getUselfId()通过自己的名字查找自己的id
 * attention.getAuserId()通过输入的名字进行查询
 * attention.getAuserName()输入的名字
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/17-23-57
 */
public class AttentionDaoImpl extends CommonDaoImpl<Attention>{
    @Override
    public int save(final Attention attention) {
        String sql = "insert into attentions(uself_id,auser_id,auser_name) values(?,?,?)";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,attention.getUselfId());
                    pstmt.setInt(2,attention.getAuserId());
                    pstmt.setString(3,attention.getAuserName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 查找关注人的集合
     * @param
     * @return
     */
    @Override
    public List<Attention> findAttByUserId(final int id) {
        String sql = "select * from attentions where useif_id = ?";
        return JdbcTemplate.query(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, createHandle());
    }

    private JdbcTemplate.RowCallBackHandle<Attention> createHandle() {
        return new JdbcTemplate.RowCallBackHandle<Attention>() {
            @Override
            public Attention processRow(ResultSet rs) {
                Attention attention = null;
                try {
                    attention = new Attention();
                    attention.setaId(rs.getInt("a_id"));
                    attention.setAuserId(rs.getInt("auser_id"));
                    attention.setAuserName(rs.getString("auser_name"));
                    attention.setUselfId(rs.getInt("uself_id"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return attention;
            }
        };
    }
}
