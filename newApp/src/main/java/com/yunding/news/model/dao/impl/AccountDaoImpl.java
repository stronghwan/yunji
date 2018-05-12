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
                    pstmt.setString(6,account.getChoice());
                    pstmt.setString(7,account.getAnswer());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
       final int id = this.findUserIdPersonal(account.getName());
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
                Account account = null;
                try {
                    account = new Account();
                    account.setId(rs.getInt("user_id"));
                    account.setName(rs.getString("user_name"));
                    account.setPassword(rs.getString("user_password"));
                    account.setSex(rs.getString("user_sex"));
                    account.setPhone(rs.getString("user_phone"));
                    account.setEmail(rs.getString("user_email"));
                    account.setChoice(rs.getString("question_choice"));
                    account.setAnswer(rs.getString("answer"));
                    account.setStatus(rs.getString("status"));
                    account.setDepartment(rs.getString("department"));
                    account.setNickName(rs.getString("user_nickName"));
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

    @Override
    public String findByuserEmail(final String email) {
        String sql = "select user_email from account where user_email = ?";
        return JdbcTemplate.SingleQuery(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,email);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        },createHandleEmail());
    }

    private JdbcTemplate.RowCallBackHandle<String> createHandleEmail() {
        return new JdbcTemplate.RowCallBackHandle<String>() {
            @Override
            public String processRow(ResultSet rs) {
                Account account = null;
                try {
                    account = new Account();
                    account.setEmail(rs.getString("user_email"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return account.getEmail();
            }
        };
    }

    /**
     * 查找到注册表的id
     * @param name 注册时的用户名
     * @return
     */
    @Override
    public int findUserIdPersonal(final String name) {
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
                    account = new Account();
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
        String sql = "update account set user_phone=?,user_email=?,question_choice=?,answer=?) " +
                "where user_name=?";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,account.getPhone());
                    pstmt.setString(2,account.getEmail());
                    pstmt.setString(3,account.getChoice());
                    pstmt.setString(4,account.getAnswer());
                    pstmt.setString(5,account.getName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int saveByStepThree(final Account account) {
        String sql = "update account set user_sex=?, user_nickName=?,department=? " +
                "where user_name = ?";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,account.getNickName());
                    pstmt.setString(2,account.getSex());
                    pstmt.setString(3,account.getDepartment());
                    pstmt.setString(4,account.getName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
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

    @Override
    public int modifiedUserInfo(final String name) {
        String sql = "update account set status = 1 where user_name = ?";
        return JdbcTemplate.update(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int findUserId(final String name) {
        String sql = "select * from account where user_name = ?";
        return JdbcTemplate.SingleQuery(sql, new JdbcTemplate.PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) {
                try {
                    pstmt.setString(1,name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        },createHandleAccount());
    }

    private JdbcTemplate.RowCallBackHandle<Integer> createHandleAccount() {
        return new JdbcTemplate.RowCallBackHandle<Integer>() {
            @Override
            public Integer processRow(ResultSet rs) {
                Account account = null;
                try {
                    account = new Account();
                    account.setId(rs.getInt("user_id"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return account.getId();
            }
        };
    }
}
