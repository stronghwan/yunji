        /*
        敏感词过滤器
        Creater：侯钧耀
        Date: 2018/4/15
        Time: 16:11
        */
package com.yunding.news.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class DataFilter {

    public DataFilter(){

    }
    public void  destroy(){

    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException {
        // MyServlet req = new MyServlet((HttpServletRequest)request);
        //chain.doFilter(req,response);
    }

    public void  init(FilterConfig fConfig) throws ServletException{

    }

}
