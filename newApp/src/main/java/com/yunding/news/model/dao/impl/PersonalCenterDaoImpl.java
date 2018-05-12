package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.PersonalCenter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @个人中心类
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/9-22-26
 */
public class PersonalCenterDaoImpl extends CommonDaoImpl<PersonalCenter>{
    /**
     * 保存新注册的用户
     * @param personalCenter
     * @return
     */
    @Override
    public int save(final PersonalCenter personalCenter) {
        String sql = "insert into personalCenter(w_sex,w_signature,w_address,w_phone,w_email,w_department,user_id,w_nikename,user_name) " +
                "values(?,?,?,?,?,?,?,?,?)";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,personalCenter.getSex());
                    pstmt.setString(2,personalCenter.getSignature());
                    pstmt.setString(3,personalCenter.getAddress());
                    pstmt.setString(4,personalCenter.getPhone());
                    pstmt.setString(5,personalCenter.getEmail());
                    pstmt.setString(6,personalCenter.getDepartment());
                    pstmt.setInt(7,personalCenter.getUserId());
                    pstmt.setString(8,personalCenter.getNickname());
                    pstmt.setString(9,personalCenter.getUserName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 通过personalCenter的user_id 查询；
     * @param name  用户名
     * @return
     */
    @Override
    public PersonalCenter findByPerUserID(String name) {
        final int id = DaoFactory.getDao("user").findUserIdPersonal(name);
        String sql = "select * from personalCenter where user_id = ?";
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

    private JdbcTemplate.RowCallBackHandle<PersonalCenter> createHandle() {
        return new JdbcTemplate.RowCallBackHandle<PersonalCenter>() {
            @Override
            public PersonalCenter processRow(ResultSet rs) {
                PersonalCenter personalCenter = null;
                try {
                    personalCenter.setSex(rs.getString("w_sex"));
                    personalCenter.setAddress(rs.getString("w_address"));
                    personalCenter.setDepartment(rs.getString("w_department"));
                    personalCenter.setEmail(rs.getString("w_email"));
                    personalCenter.setSignature(rs.getString("w_signature"));
                    personalCenter.setPhone(rs.getString("w_phone"));
                    personalCenter.setNickname(rs.getString("w_nikename"));
                    personalCenter.setUserName(rs.getString("user_name"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return personalCenter;
            }
        };
    }

    @Override
    public int modifiedUserInfo(final PersonalCenter personalCenter) {
        String sql = "update personalCenter set w_sex = ?,w_signature = ?,w_address = ?,w_phone = ?," +
                "w_email = ?,w_department = ?,w_nikename = ? where user_name = ?";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,personalCenter.getSex());
                    pstmt.setString(2,personalCenter.getSignature());
                    pstmt.setString(3,personalCenter.getAddress());
                    pstmt.setString(4,personalCenter.getPhone());
                    pstmt.setString(5,personalCenter.getEmail());
                    pstmt.setString(6,personalCenter.getDepartment());
                    pstmt.setString(7,personalCenter.getNickname());
                    pstmt.setString(8,personalCenter.getUserName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
