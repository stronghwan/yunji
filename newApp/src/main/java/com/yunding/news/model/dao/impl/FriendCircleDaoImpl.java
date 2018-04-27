package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.pojo.FriendCircle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @朋友圈实现类
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/20-14-00
 */
public class FriendCircleDaoImpl extends CommonDaoImpl<FriendCircle>{
    /**
     * 添加说说
     * @param friendCircle 说说对象
     * @return 添加了几条数据
     */
    @Override
    public int save(final FriendCircle friendCircle) {
        String sql = "insert into friend_circles(user_id,f_create_time,f_content) values(?,?,?)";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,friendCircle.getfId());
                    pstmt.setDate(2, (Date) friendCircle.getCreateTime());
                    pstmt.setString(3,friendCircle.getfContent());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 查找朋友圈中所有的说说
     * @return 所有说说的集合
     */
    @Override
    public List<FriendCircle> findAll() {
        String sql = "select * from friend_circles";
        return JdbcTemplate.query(sql,null,creaHandle());
    }

    private JdbcTemplate.RowCallBackHandle<FriendCircle> creaHandle() {
        return new JdbcTemplate.RowCallBackHandle<FriendCircle>() {
            @Override
            public FriendCircle processRow(ResultSet rs) {
                FriendCircle friendCircle = null;
                try {
                    friendCircle =new FriendCircle();
                    friendCircle.setfId(rs.getInt("f_id"));
                    friendCircle.setfUserId(rs.getInt("user_id"));
                    friendCircle.setCreateTime(rs.getTime("f_create_time"));
                    friendCircle.setfContent(rs.getString("f_content"));
                    friendCircle.setUserName(rs.getString("user_name"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return friendCircle;
            }
        };
    }

    @Override
    public List<FriendCircle> findByUserId(final int id) {
        String sql = "select * from friend_circles where user_id = ?";
        return JdbcTemplate.query(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        },creaHandle());
    }
}
