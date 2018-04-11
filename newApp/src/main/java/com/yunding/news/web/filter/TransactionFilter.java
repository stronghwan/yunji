package com.yunding.news.web.filter;


import com.yunding.news.common.Transaction;

import javax.servlet.*;
import java.io.IOException;

/**
 * @过滤事务并且关闭连接
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/9-19-29
 */
public class TransactionFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            Transaction.begin();
            filterChain.doFilter(servletRequest,servletResponse);
            Transaction.commit();
        }catch (IOException e){
            e.printStackTrace();
            Transaction.rollBack();
        }
    }

    public void destroy() {

    }
}
