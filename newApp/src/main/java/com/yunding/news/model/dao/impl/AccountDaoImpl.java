package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.pojo.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/3-00-18
 */
public class AccountDaoImpl extends CommonDaoImpl<Account>{
    /**
     * 保存的时侯在个人中心表中增加id
     * @param account
     * @return
     */
    @Override
    public int save(final Account account) {
        String sql = "insert into account(user_name,user_password,user_sex,user_phone,user_email,question_choice,answer) values(?,?,?,?,?,?,?)";
        JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            public void setValues(PreparedStatement pstmt) {

                try {
                    pstmt.setString(1,account.getName());
                    pstmt.setString(2,account.getPassword());
                    pstmt.setString(3,account.getSex());
                    pstmt.setString(4,account.getPhone());
                    pstmt.setString(5,account.getEmail());
                    pstmt.setInt(6,account.getChoice());
                    pstmt.setString(7,account.getAnswer());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
       final int id = this.findUserId(account.getName());
       String sql2 = "insert into personalCenter(user_id) values(?)";
        return JdbcTemplate.update(sql2, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setInt(1,id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private JdbcTemplate.RowCallBackHandle<Account> createHandles() {
        return new JdbcTemplate.RowCallBackHandle<Account>() {
            public Account processRow(ResultSet rs) {
                Account account = new Account();
                try {
                    account.setId(rs.getInt("user_id"));
                    account.setName(rs.getString("user_name"));
                    account.setPassword(rs.getString("user_password"));
                    account.setSex(rs.getString("user_sex"));
                    account.setPhone(rs.getString("user_phone"));
                    account.setEmail(rs.getString("user_email"));
                    account.setChoice(rs.getInt("question_choice"));
                    account.setAnswer(rs.getString("answer"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return account;
            }
        };
    }

    @Override
    public Account findByUserName(final String name) {
        String sql = "select * from account where user_name = ?";
        return JdbcTemplate.SingleQuery(sql, new JdbcTemplate.PreparedStatementSetter() {
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        },createHandles());
    }

    /**
     * 查找到注册表的id
     * @param name 注册时的用户名
     * @return
     */
    @Override
    public int findUserId(final String name) {
        String sql = "select w_id from personalCenter where user_name = ?";
        return JdbcTemplate.SingleQuery(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        },createHandle());
    }

    private JdbcTemplate.RowCallBackHandle<Integer> createHandle() {
        return new JdbcTemplate.RowCallBackHandle<Integer>() {
            @Override
            public Integer processRow(ResultSet rs) {
                Account account = null;
                try {
                    account.setId(rs.getInt("user_id"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return account.getId();
            }
        };
    }


    /**
     * 通过用户名添加注册信息
     * @param account 用户名
     * @return
    */

    @Override
    public int saveByStep(final Account account) {
        String sql = "insert into account(user_name,user_password) values(?,?)";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,account.getName());
                    pstmt.setString(2,account.getPassword());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int saveByStepTwo(final Account account) {
        String sql = "insert into account(user_phone,user_email,question_choice,answer) values(?,?,?,?) where user_name=?";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,account.getPhone());
                    pstmt.setString(2,account.getEmail());
                    pstmt.setInt(3,account.getChoice());
                    pstmt.setString(4,account.getAnswer());
                    pstmt.setString(5,account.getName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int saveByStepThree(Account account) {
        // 暂时先返回零
        return 0;
    }

    /**
     *  通过找回密码修改密码的值
     * @param account
     * @return
     */
    @Override
    public int modifiedUserInfo(final Account account) {
        String sql = "update account set user_password = ? where user_name = ?";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,account.getPassword());
                    pstmt.setString(1,account.getName());

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
