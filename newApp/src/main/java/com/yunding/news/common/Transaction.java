package com.yunding.news.common;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务处理工具类
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/9-19-11
 */
public final class Transaction {
    public static void begin(){
        try {
            Connection conn = ConnectionManager.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(){
        try {
            Connection conn = ConnectionManager.getConnection();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollBack(){
        try {
            ConnectionManager.getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionManager.release();
        }
    }

    public static class TransactionExpection extends Exception{
        public TransactionExpection() {
            super();
        }

        public TransactionExpection(String message) {
            super(message);
        }

        public TransactionExpection(String message, Throwable cause) {
            super(message, cause);
        }

        public TransactionExpection(Throwable cause) {
            super(cause);
        }

        protected TransactionExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
